package Duke;

import Duke.Exception.InvalidTaskException;
import Duke.Exception.MissingDescriptonException;
import Duke.Exception.MissingNumberFieldException;
import Duke.Exception.MissingTimeFieldException;


/**
 * The main class that first get run
 */

import java.time.DateTimeException;
import java.time.format.DateTimeParseException;


public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Create the respective classes that will be used in duke
     */
    public Duke() {
            ui = new Ui();
            storage = new Storage();
            tasks = new TaskList();
            parser = new Parser();
    }

    /**
     * The actual execution of Duke is summarised in this method. Duke is first entered, then the commands are fed till the exit command is found. Finally, the duke exits
     */
    public void run() {
        enterDuke();
        runDukeTillExit();
        exitDuke();

    }

    /**
     * Display the welcome screen
     * <p></p>
     * <p>Followed by importing the offline saved data into the list of tasks</p>
     * @see Ui
     * @see TaskList#setTaskList
     * @see Storage#load
     */
    private void enterDuke() {
        ui.displayHello();
        tasks.setTaskList(storage.load());
    }

    /**
     * The method where Duke reads and executes the user inputs
     * <p></p>
     * <p>Will keep running until "bye" is seen. Then, it will quit the duke program itself</p>
     * @see Ui#getUserInput()
     * @see Parser#parseUserInput
     * @see Command#execute
     */
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
                    | NumberFormatException
                    | DateTimeException m) {
                System.out.println("Exception occurred: " + m);
            }
            ui.displayPrompt();
            userInput = ui.getUserInput();
        }
    }

    /**
     * The method used to exit Duke. Occurs when the "bye" command is issued. It saves the task list and close the scanner before saying goodbye
     * @see Storage#save
     * @see Ui#closeScanner
     * @see Ui#displayGoodbye()
     */
    private void exitDuke() {
        storage.save(tasks.getTaskList(), tasks.getNumberOfTask());
        ui.closeScanner();
        ui.displayGoodbye();
    }

    public static void main(String[] args) {
        new Duke().run();
    }


}
