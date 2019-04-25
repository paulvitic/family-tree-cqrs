package energy.getfresh.homework.port.adaptor.console;

import energy.getfresh.homework.application.familymember.AddChildCommand;
import energy.getfresh.homework.application.familymember.FamilyMemberService;
import energy.getfresh.homework.application.familymember.GetRelativesCommand;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class Dialog {

    private final String defaultFilePath;

    private final String defaultFile;

    private final FamilyMemberService familyService;

    private Dialog(FamilyMemberService familyService, String defaultFilePath, String defaultFile){
        super();
        this.defaultFilePath = defaultFilePath;
        this.defaultFile = defaultFile;
        this.familyService = familyService;
    }

    public Dialog(FamilyMemberService familyService){
        this(familyService, "./input/", "input.txt");
    }


    public void start() {
        defaultFile();
        System.out.println("Welcome to family tree. Current tree can be viewed at ./view/tree.html");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        inputFile(reader);
        try {
            reader.close();
        } catch (IOException ignore) {}
        System.out.println("Final tree can be viewed at ./view/tree.html. Bye!");
    }



    private void defaultFile() {
        log.debug("Current path is " + FileSystems.getDefault().getPath("").toUri().getPath());
        File file = new File(defaultFilePath + defaultFile);
        if (!file.exists()) {
            try {
                if (new File(defaultFilePath).mkdirs()) {
                    file.createNewFile();
                }
            } catch (IOException e) {
                log.error("Error while creating default input file.", e);
            }
        }
    }



    private void inputFile(BufferedReader reader){
        System.out.print("Enter input file location (default " + defaultFilePath + defaultFile + "): ");
        try {
            exec(reader.readLine());
            if (!exit(reader)){
                inputFile(reader);
            }
        } catch (IOException e) {
            System.out.println("Error reading input file location: " + e.getMessage());
        }
    }


    private boolean exit(BufferedReader reader) {
        System.out.print("Exit (y/n): ");
        try {
            return reader.readLine().toUpperCase().equals("Y");
        } catch (IOException e) {
            System.out.println("Error reading exit command: " + e.getMessage() + ". Exiting.");
        }
        return true;
    }


    private void exec(String filePath){
        loadFile(filePath.isEmpty() ? defaultFilePath + defaultFile : filePath)
                .ifPresent(commands -> commands
                        .forEach(command -> {
                            String[] words = command.split(" ");
                            if (words.length == 2){
                                Set<String> relatives = familyService.namesOfRelatives(
                                        new GetRelativesCommand(words[0], words[1]));
                                relatives.forEach(relative -> System.out.print(relative + " "));
                                System.out.print("\n");
                            } else if (words.length == 3){
                                familyService.addChild(new AddChildCommand(words[0], words[1], words[2]));
                            } else {
                                System.out.format("%s command is not recognized.\n", command);
                            }
        }));
    }


    Optional<List<String>> loadFile(String file) {
        try {
            return Optional.of(
                    Files.lines(
                            FileSystems.getDefault().getPath(file))
                            .collect(Collectors.toList()));
        } catch (IOException e) {
            System.out.println("Error while reading input file: " + e.getMessage());
        }
        return Optional.empty();
    }
}
