import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    private static String underscoredLine = "\t____________________________________________________________";

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFileToTaskList());
        } catch (FileNotFoundException e) {
            //ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        Duke main = new Duke("data/duke.txt");
        main.runStartup();
        main.runLoopUntilExit();
        main.runExit();
    }

    private void runStartup() {
        this.ui = new Ui();
        ui.sayIntro();
    }

    public void runLoopUntilExit() {
        Parser commandParser = new Parser(tasks);
        while (true) {
            String nextCommand = ui.getUserCommand();
            /*processedCommand = */
            commandParser.processCommand(nextCommand);
            if (commandParser.isExitCommandInvoked) {
                break;
            }
        }
    }

    public void runExit() {
        //TODO: save tasklist to storage
        try {
            storage.saveTaskListToFile(tasks);
        } catch (IOException e) {
            System.out.println(underscoredLine);
            System.out.println("\tError saving taskList to duke.txt");
            System.out.println(underscoredLine);
        }

        ui.sayGoodbye();
        System.exit(0);
    }

}
