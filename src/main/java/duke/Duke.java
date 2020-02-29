package duke;

import java.io.FileNotFoundException;

public class Duke {
    private static TaskList tasks;
    protected static Storage storage;
    private static Parser parser;

    public Duke(String filePath) {
        tasks = new TaskList();
        storage = new Storage(filePath);
        parser = new Parser();

        try {
            storage.loadFileData(tasks);
        } catch (FileNotFoundException e) {
            Ui.printFileNotFoundInfo();
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    public void run(){
        prepare();
        chooseOneModeAndRun();
        exit();
    }

    public static void prepare(){
        Ui.showWelcomeMessage();
    }

    public static void exit(){
        Ui.sayBye();
    }

    private static void chooseOneModeAndRun() {
        String input = parser.getAndProcessInput();
        boolean isInputValid = false;

        while (!isInputValid) {
            switch (input) {
            case "mode 1":
                runInEchoMode();
                isInputValid = true;
                break;
            case "mode 2":
                runInCommandMode();
                isInputValid = true;
                break;
            default:
                Ui.showUnknownModeInfo();
                input = parser.getAndProcessInput();
                break;
            }
        }
    }

    public static void runInEchoMode() {
        Ui.showEchoModeGuideInfo();
        String input = parser.getAndProcessInput();

        while (!parser.isByeCommand(input)) {
            Ui.repeatInput(input);
            input = parser.getAndProcessInput();
        }
    }

    public static void runInCommandMode() {
        Ui.showCommandModeGuideInfo();
        String input = parser.getAndProcessInput();

        while (!parser.isByeCommand(input)) {
            Ui.showSplitLine();
            parseAndExecuteCommand(input,storage);
            Ui.showSplitLine();
            input = parser.getAndProcessInput();
        }
    }

    private static void parseAndExecuteCommand(String input,Storage storage) {
        if(parser.isDoneCommand(input)){
            tasks.setTaskAsDoneAndSave(input,storage);
        }else if(parser.isDeleteCommand(input)) {
            tasks.removeCertainTaskAndSave(input,storage);
        } else if(parser.isListCommand(input)){
            tasks.printTaskList();
        } else if(parser.isFindCommand(input)){
            String targetWords = parser.getTargetWords(input);
            tasks.showAllRelatedTasks(targetWords);
        } else tasks.addNewTaskAndSave(input,storage);
    }
}
