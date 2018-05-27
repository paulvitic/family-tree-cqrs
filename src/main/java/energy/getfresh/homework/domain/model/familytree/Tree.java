package energy.getfresh.homework.domain.model.familytree;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Slf4j
public class Tree {

    @JsonProperty("name")
    private String name;
    @JsonProperty("class")
    private NodeType gender;
    @JsonProperty("marriages")
    List<Node> marriages;

    public Tree() {
        this.marriages = new ArrayList<>();
    }

    public Tree(String name, NodeType gender) {
        this();
        this.name = name;
        this.gender = gender;
    }

    public String name(){
        return name;
    }

    public void marry(String spouse, NodeType gender) {
        if (marriages==null){
            marriages = new ArrayList<>();
        }
        marriages.add(new Node(new Tree(spouse, gender)));
    }

    public Optional<Node> marriageTo(String spouse){
        return marriages
                .stream()
                .filter(m -> m.spouse().name().equals(spouse))
                .findAny();
    }

    public Stream<Tree> flattened() {
        return Stream.concat(
                Stream.of(this),
                marriages.stream()
                        .flatMap(m -> m.children()
                                .stream()
                                .flatMap(Tree::flattened)));
    }

    public boolean isMale() {
        return gender==NodeType.man;
    }
}
