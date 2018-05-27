package energy.getfresh.homework.port.adaptor.view;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import energy.getfresh.homework.domain.model.familytree.NodeType;
import energy.getfresh.homework.domain.model.familytree.Tree;
import energy.getfresh.homework.infrastructure.queue.Message;
import energy.getfresh.homework.infrastructure.queue.MessageConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class TreeView extends MessageConsumer {

    private final String viewFile = "tree.html";
    private final String viewPath;
    private final List<Tree> tree;

    private static TreeView instance;

    private TreeView(String viewPath,
                     List<Tree> tree) {
        this.viewPath = viewPath;
        this.tree = tree;
    }


    public static TreeView get(String viewPath) {
        if (instance == null) {
            instance = new TreeView(viewPath, new ArrayList<>());
            Velocity.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
            Velocity.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
            Velocity.init();
        }
        return instance;
    }


    public static TreeView get() {
        return get("./view/");
    }


    @Override
    public void run() {
        try {
            Message msg;
            while (!(msg = queue.take()).getType().equals("Exit")) {
                onMessage(msg);
            }
        } catch (InterruptedException e) {
            log.error("Error while consuming queue." , e);
        }
        log.info("Consumer stopped.");
    }


    protected void onMessage(Message msg) {
        readContent(msg).ifPresent(content -> {
            String type = msg.getType();
            switch (type) {
                case "PersonAdded":
                    addRoot(content);
                    return;
                case "ChildAdded":
                    addChild(content);
                    return;
                case "SpouseAdded":
                    addSpouse(content);
                    return;
                default:
                    log.warn("Message type {} not known", type);
            }
        });
    }


    private void addRoot(Map<String, String> content){
        try {
            assert content.get("name") != null;
            assert content.get("gender") != null;

            NodeType.get(content.get("gender"))
                        .ifPresent(type -> tree.add(new Tree(content.get("name"), type)));
            generate();
        } catch (AssertionError e){
            log.warn("Error while adding root.", e);
        }
    }


    private void addSpouse(Map<String, String> content){
        try {
            assert content.get("name") != null;
            assert content.get("spouseName") != null;
            assert content.get("spouseGender") != null;

            NodeType.get(content.get("spouseGender"))
                    .ifPresent(gender -> tree.stream()
                            .flatMap(nodes -> nodes.flattened()
                                    .filter(node -> node.name().equals(content.get("name"))))
                            .findAny()
                            .ifPresent(found -> {
                                found.marry(content.get("spouseName"), gender);
                                generate();
                            }));
        } catch (AssertionError e) {
            log.warn("Error while adding spouse.", e);
        }
    }


    private void addChild(Map<String, String> content){
        try {
            assert content.get("fathersName") != null;
            assert content.get("mothersName") != null;
            assert content.get("childsName") != null;
            assert content.get("childsGender") != null;

            NodeType.get(content.get("childsGender"))
                    .ifPresent(gender -> tree.stream()
                            .flatMap(nodes -> nodes.flattened()
                                    .filter(node -> node.name().equals(content.get("fathersName")) ||
                                            node.name().equals(content.get("mothersName"))))
                            .findAny()
                            .ifPresent(found -> {
                                String spouseName = found.isMale() ? content.get("mothersName") : content.get("fathersName");
                                found.marriageTo(spouseName)
                                        .ifPresent(marriage -> marriage.addChild(content.get("childsName"), gender));
                                generate();
                            }));
        } catch (AssertionError e) {
            log.warn("Error while adding child.", e);
        }
    }


    private void generate() {
        treeData()
                .ifPresent(treeData -> viewWriter()
                        .ifPresent(viewWriter -> {
                            VelocityContext context = new VelocityContext();
                            context.put("treeData", treeData);
                            Velocity.mergeTemplate("view/tree.vm", "UTF-8", context, viewWriter);
                            log.info("Generated view.");
                            try {
                                viewWriter.close();
                            } catch (IOException ignore) {}
                        }));
    }


    private Optional<String> treeData() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return Optional.of(mapper.writeValueAsString(tree));
        } catch (JsonProcessingException e) {
            log.warn("Error while serializing tree data.", e);
        }
        return Optional.empty();
    }


    private Optional<Writer> viewWriter() {
        return Optional.ofNullable(viewFile()
                .map(viewFile -> {
                    try {
                        return new FileWriter(viewFile);
                    } catch (IOException e) {
                        log.warn("Error while opening file writer", e);
                    }
                    return null;
                })
                .orElse(null));
    }


    private Optional<File> viewFile() {
        File file = new File(viewPath + "/" + viewFile);
        if (!file.exists()) {
            try {
                if (new File(viewPath).mkdirs() && file.createNewFile()) {
                    return Optional.of(file);
                }
            } catch (IOException e) {
                log.error("Error while creating view file.", e);
                return Optional.empty();
            }
        }
        return Optional.of(file);
    }


    private Optional<Map<String, String>> readContent(Message msg) {
        ObjectMapper mapper = new ObjectMapper();
        JavaType javaType = mapper.getTypeFactory().constructMapType(Map.class, String.class, String.class);
        try {
            return Optional.of(mapper.readValue(msg.getMsg(), javaType));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
