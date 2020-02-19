package duke;

import duke.exception.InvalidCommandException;
import duke.exception.InvalidDateFormatException;
import duke.exception.NoDateException;
import duke.exception.NoDescException;
import duke.parser.Parser;
import duke.parser.ScannedCommand;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


public class Duke {

    private static final String DATA_FILE_PATH = "data/data.csv";
    private static Storage storage;
    private static TaskList taskList;
    private static Parser parser;

    public Duke(String dataFilePath) {
        storage = new Storage(dataFilePath);
        parser = new Parser();
        createTaskList();
    }

    private void createTaskList() {
        if (storage.loadFile()) {
            taskList = new TaskList(storage.dataFile);
        } else {
            taskList = new TaskList();
        }
    }

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

        System.out.println("Goodbye!");
    }

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
        }
        System.out.println("You have " + taskList.getSize() + " task/s. (type 'list' to list your tasks)");
        System.out.println("Anything else? Remember that you can leave by typing 'bye'.");
    }

}
