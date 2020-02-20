package Duke;

import Duke.Exception.*;


import java.time.DateTimeException;

/**
 * The main class that runs Duke
 */
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

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * The actual execution of Duke is summarised in this method. Duke is first entered, then the commands are fed till
     * the exit command is found. Finally, the duke exits
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
        Ui.displayHello();
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
        while (parser.isNotBye(userInput)) {
            try {
                command = parser.parseUserInput(userInput);
                command.execute(tasks);
            } catch (InvalidTaskException
                    | MissingDescriptionException
                    | MissingNumberFieldException
                    | MissingTimeFieldException
                    | NumberFormatException
                    | DateTimeException
                    | IndexOutOfBoundsException
                    | MissingSlashWordException m) {
                Ui.displayExceptionError(m);
            }
            Ui.displayPrompt();
            userInput = ui.getUserInput();
        }
    }

    /**
     * The method used to exit Duke. Occurs when the "bye" command is issued. It saves the task list and close the
     * scanner before saying goodbye
     * @see Storage#save
     * @see Ui#closeScanner
     * @see Ui#displayGoodbye()
     */
    private void exitDuke() {
        storage.save(tasks.getTaskList(), tasks.getNumberOfTask());
        ui.closeScanner();
        Ui.displayGoodbye();
    }


}
