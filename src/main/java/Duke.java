import java.io.FileNotFoundException;
import java.io.IOException;
import common.Messages;
import exceptions.*;
import tasklist.TaskList;

import static common.Messages.SAVE_TASKLIST_TO_FILE_FAILURE_MESSAGE;

/**
 * This is the main class that runs the entire Duke program.
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Messages messageContainer = new Messages();

    /**
     * This constructor initializes the other classes to be used in the Duke program.
     * <p></p>
     * <p>
     * The constructor will also attempt to to load any existing local TaskList save at the location specified in the filePath.
     * </p>
     * @param filePath the location of the file that contains the local save data of the TaskList (if any)
     * @see Ui
     * @see TaskList
     * @see Storage
     * @see Storage#loadFileToTaskList
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.loadFileToTaskList());
        } catch (FileNotFoundException e) {
            this.tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        Duke main = new Duke("data/duke.txt");
        main.runStartup();
        main.runLoopUntilExit();
        main.runExit();
    }

    /**
     * Displays the startup message.
     * <p></p>
     * @see Ui#sayIntro
     */
    public void runStartup() {
        ui.sayIntro();
    }

    /**
     * This method runs the main loop where where Duke obtains command from user and converts it into a {@link Command}
     * object that executes the corresponding operation.
     * <p></p>
     * <p>This loop continues running until it encounters the "bye" command. It will then exit the loop.</p>
     * @see Ui#getUserCommand
     * @see Parser#parseCommand
     * @see Command#execute
     */
    public void runLoopUntilExit() {
        Parser commandParser = new Parser(tasks);
        while (commandParser.exitCommandNotEncountered()) {
            try {
                String userInputText = ui.getUserCommand();
                Command nextCommand = commandParser.parseCommand(userInputText);
                nextCommand.execute(tasks, ui);
            } catch (NoRemarkException
                    | IllegalKeywordException
                    | NoDescriptionException
                    | NumberFieldException
                    | MissingParameterException e) {
                ui.displayMessage(e.getMessage());
            }
        }
    }

    /**
     * This method is used to exit Duke.
     * <p></p>
     * <p>Occurs when the "bye" command is issued. It saves the current task list and close the
     * scanner before saying goodbye</p>
     * @see Storage#saveTaskListToFile
     * @see Ui#closeScanner
     * @see Ui#sayGoodbye
     */
    public void runExit() {
        try {
            storage.saveTaskListToFile(tasks);
        } catch (IOException e) {
            ui.displayMessage(SAVE_TASKLIST_TO_FILE_FAILURE_MESSAGE);
        }

        ui.sayGoodbye();
        System.exit(0);
    }

}
