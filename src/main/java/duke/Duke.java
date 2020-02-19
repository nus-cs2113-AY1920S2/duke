package duke;

import duke.exception.InvalidCommandException;
import duke.exception.InvalidDateFormatException;
import duke.exception.NoDateException;
import duke.exception.NoDescException;
import duke.refactor.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Scanner;  // User input


public class Duke {

    private static final String DATA_FILE_PATH = "data/data.csv";
    private static Storage storage;
    private static TaskList taskList;

    public Duke(String dataFilePath) {
        storage = new Storage(dataFilePath);
        if (storage.loadFile()) {
            taskList = new TaskList(storage.dataFile);
        } else {
            taskList = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke(DATA_FILE_PATH);
        Ui.intro();

        // Prepare for first input
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("What can I do for you?");

        // Note: Scanner.next() reads until delimiter, Scanner.nextLine() reads until EOL
        // If Scanner.next() is called first, then Scanner.nextLine() reads from that point onwards
        // e.g. if the user inputs: 'deadline read book \5pm', the two strings below will contain:
        //      userCommand = 'deadline'
        //      userParams  = 'read book \5pm'
        String userCommand = inputScanner.next(); // Read first word of input
        String userParams = inputScanner.nextLine(); // Get user input after first word

        // Main execution loop
        while(!userCommand.equalsIgnoreCase("bye")) {
            try {
                Parser.parseCommand(userCommand, userParams, taskList, storage);
            } catch (NoDescException e) {
                Ui.formatPrint("Please input a description.");
            } catch (NoDateException e) {
                Ui.formatPrint("Please input a date.");
            } catch (InvalidDateFormatException e) {
                Ui.formatPrint("Invalid input method. Please input the task in the following format: ");
                switch (userCommand) {
                case "deadline":
                    Ui.formatPrint("deadline description /date");
                    break;
                case "event":
                    Ui.formatPrint("event description /date");
                    break;
                }
            } catch (InvalidCommandException e) {
                Ui.formatPrint("Sorry, I didn't recognize that command.");
            }

            System.out.println("You have " + taskList.getSize() + " task/s. (type 'list' to list your tasks)");
            System.out.println("Anything else? Remember that you can leave by typing 'bye'.");

            userCommand = inputScanner.next(); // Prepare for next user command
            userParams = inputScanner.nextLine(); // Get user input after first word
        }
        System.out.println("Goodbye!");
    }
}
