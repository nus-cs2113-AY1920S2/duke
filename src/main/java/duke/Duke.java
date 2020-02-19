package duke;

import duke.exception.*;
import duke.parser.Parser;
import duke.parser.ScannedCommand;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Main class for the Duke program.
 */
public class Duke {

    /** Constant file path of data file. */
    private static final String DATA_FILE_PATH = "data/data.csv";

    /** Storage object for data file. */
    private static Storage storage;

    /** Task list to store current tasks in. */
    private static TaskList taskList;

    /** Parser object to scan and parse user input. */
    private static Parser parser;

    /**
     * Constructs Duke, loading data from the specified file path.
     * @param dataFilePath File path to data file.
     */
    public Duke(String dataFilePath) {
        storage = new Storage(dataFilePath);
        parser = new Parser();
        createTaskList();
    }

    /**
     * Creates TaskList and loads data from data file if the data file previously existed;
     * otherwise, an empty task list is initialized.
     */
    private void createTaskList() {
        if (storage.loadFile()) {
            taskList = new TaskList(storage.dataFile);
        } else {
            taskList = new TaskList();
        }
    }

    /**
     * Main running function.
     * @param args CLI arguments when starting file.
     */
    public static void main(String[] args) {
        new Duke(DATA_FILE_PATH);
        Ui.displayWelcome();

        ScannedCommand inputCommand;
        String userCommand;
        String userParams;

        do {
            inputCommand = parser.scanCommand();
            userCommand = inputCommand.getUserCommand();
            userParams = inputCommand.getUserParams();
            run(userCommand, userParams);
        } while (!userCommand.equalsIgnoreCase("bye"));

    }

    /**
     * Parses command and catches exceptions for unusual input.
     *
     * @param userCommand Command input by the user.
     * @param userParams Any parameters input by the user.
     */
    private static void run(String userCommand, String userParams) {
        try {
            Parser.parseCommand(userCommand, userParams, taskList, storage);
        } catch (NoDescException e) {
            Ui.formatPrint("Please input a description.");
        } catch (NoDateException e) {
            Ui.formatPrint("Please input a date.");
        } catch (InvalidDateFormatException e) {
            Ui.formatPrint("Invalid input method. Please input the task in the following format: ");
            Ui.printCorrectDateFormat(userCommand);
        } catch (InvalidCommandException e) {
            Ui.formatPrint("Sorry, I didn't recognize that command.");
        } catch (NoFindException e) {
            Ui.formatPrint("Please input the string you want to find.");
        }
        System.out.println("You have " + taskList.getSize() + " task/s. (type 'list' to list your tasks)");
        System.out.println("Anything else? Remember that you can leave by typing 'bye'.");
    }

}
