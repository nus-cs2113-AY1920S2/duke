package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.File;
import java.util.Scanner;

/**
 * Main Class of the application.
 */
public class Duke {

    private static final String FILE_PATH = "data" + File.separator + "TaskList.txt";
    private static final String BYE_COMMAND = "bye";

    /** Used for storing and loading tasks. */
    private static Storage storage;
    /** Used for interacting with the user. */
    private static Ui ui;
    /** Used for parsing user commands. */
    private static Parser parser;
    /** Used to store and manipulate the list of tasks. */
    private static TaskList taskList;

    /**
     * Constructor for Duke Class.
     * It creates a new Duke Object initialised with task list at the specified file path.
     *
     * @param filePath The location of the data file where tasks were previously stored at.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        parser = new Parser();
        taskList = new TaskList(storage.loadData());
    }

    /**
     * Runs the entire application and stores the task list before exit by calling relevant functions such as
     * {@link Ui#printWelcomeMessage()}, {@link #parseAndExecuteCommands()}, {@link Storage#storeTasksToFile(TaskList)}
     * and {@link Ui#printByeMessage()}.
     *
     * @see Ui#printWelcomeMessage()
     * @see #parseAndExecuteCommands()
     * @see Storage#storeTasksToFile(TaskList)
     * @see Ui#printByeMessage()
     */
    public void run() {
        ui.printWelcomeMessage();
        parseAndExecuteCommands();
        storage.storeTasksToFile(taskList);
        ui.printByeMessage();
    }

    /**
     * Parses the user commands and executes them till the bye command is entered. After which it stops and returns the
     * control back to {@link #run()}.
     */
    private void parseAndExecuteCommands() {
        Scanner sc = new Scanner(System.in);
        String fullCommand;
        Command command;

        fullCommand = sc.nextLine();
        while (!fullCommand.equals(BYE_COMMAND)) {
            try {
                command = parser.parseCommand(fullCommand);
                command.executeCommand(taskList);
            } catch (DukeException dukeException) {
                dukeException.printExceptionMessage();
            }
            ui.printEmptyLine();
            fullCommand = sc.nextLine();
        }
    }

    /**
     * Main function of the entire application.
     * Starts the application by creating a Duke object, initializing it
     * and then calling the {@link #run()} function of the created Duke Object.
     *
     * @param args Command Line Arguments, Not used in the application.
     */
    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }

}
