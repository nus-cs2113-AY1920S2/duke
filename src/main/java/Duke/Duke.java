package Duke;

import Duke.Exception.InvalidTaskException;
import Duke.Exception.MissingDescriptonException;
import Duke.Exception.MissingNumberFieldException;
import Duke.Exception.MissingTimeFieldException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;


    public Duke() {
            ui = new Ui();
            storage = new Storage();
            tasks = new TaskList();
            parser = new Parser();
    }

    public void run() {
        enterDuke();
        runDukeTillExit();
        exitDuke();

    }

    private void enterDuke() {
        ui.displayHello();
        tasks.setTaskList(storage.load());
    }

    private void runDukeTillExit() {
        String userInput = ui.getUserInput();
        Command command;
        while (parser.isNotBye(userInput)){
            try {
                command = parser.parseUserInput(userInput);
                command.execute(tasks);
            } catch (InvalidTaskException
                    | MissingDescriptonException
                    | MissingNumberFieldException
                    | MissingTimeFieldException
                    | NumberFormatException m) {
                System.out.println("Exception occurred: " + m);
            }
            ui.displayPrompt();
            userInput = ui.getUserInput();
        }
    }

    private void exitDuke() {
        storage.save(tasks.getTaskList(), tasks.getNumberOfTask());
        ui.closeScanner();
        ui.displayGoodbye();
    }

    public static void main(String[] args) {
        new Duke().run();
    }


}
