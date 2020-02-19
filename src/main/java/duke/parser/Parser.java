package duke.parser;

import duke.exception.*;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Scans the user input and parses the commands.
 */
public class Parser {

    /** Scanner object to read user input. */
    Scanner inputScanner;

    /**
     * Constructs a Parser object by initializing a new Scanner.
     */
    public Parser() {
        inputScanner = new Scanner(System.in);
    }

    /**
     * Scans the latest user input, reading the command and the parameters separately.
     *
     * @return ScannedCommand object comprising both the command and the parameters.
     */
    public ScannedCommand scanCommand() {
        // Note: Scanner.next() reads until delimiter, Scanner.nextLine() reads until EOL
        // If Scanner.next() is called first, then Scanner.nextLine() reads from that point onwards
        // e.g. if the user inputs: 'deadline read book \5pm', the two strings below will contain:
        //      userCommand = 'deadline'
        //      userParams  = 'read book \5pm'
        String userCommand = inputScanner.next(); // Read first word of input
        String userParams = inputScanner.nextLine(); // Get user input after first word

        return new ScannedCommand(userCommand, userParams);
    }


    /**
     * Parses the scanned input and executes commands accordingly.
     *
     * @param userCommand Command input by the user.
     * @param userParams Any subsequent parameters input by the user.
     * @param taskList The current session's TaskList.
     * @param storage The current session's Storage (where the data file is stored).
     * @throws NoDescException If description for a task is missing.
     * @throws NoDateException If date for a task is missing.
     * @throws NoFindException If string to find is missing when using the 'find' command.
     * @throws InvalidDateFormatException If date for a task is not formatted properly.
     * @throws InvalidCommandException If command is invalid.
     */
    public static void parseCommand(String userCommand, String userParams,
                                    TaskList taskList, Storage storage)
            throws NoDescException, NoDateException, InvalidDateFormatException,
                InvalidCommandException, NoFindException {
        switch (userCommand) {
        case "todo":
            parseTodo(userParams, taskList, storage);
            break;
        case "deadline":
            // Fallthrough
        case "event":
            parseDateTask(userCommand, userParams, taskList, storage);
            break;
        case "done":
            // Fallthrough
        case "delete":
            parseTaskModification(userCommand, userParams, taskList, storage);
            break;
        case "find":
            parseFind(userParams, taskList);
            break;
        case "list":
            Ui.formatPrint(taskList);
            break;
        case "bye":
            Ui.formatPrint("Goodbye!");
            break;
        default:
            throw new InvalidCommandException();
            // Note: break statement not needed here because the exception is thrown, stopping flow
        }
    }

    /**
     * Parses commands that modify a task, by either marking it as done or deleting it.
     *
     * @param userCommand Command input by the user.
     * @param userParams String containing the ID of the task to mark as done or delete.
     * @param taskList Current session's TaskList.
     * @param storage Current session's Storage.
     */
    private static void parseTaskModification(String userCommand, String userParams,
                                              TaskList taskList, Storage storage) {
        String stringId = userParams.replaceAll("[^0-9]", ""); // Extract numeric characters
        int taskId = Integer.parseInt(stringId) - 1;
        if (userCommand.equals("done")) {
            taskList.setDone(taskId, storage);
        } else {
            taskList.deleteTask(taskId, storage);
        }
    }

    /**
     * Parses tasks with a LocalDate property (e.g. Deadline and Event).
     *
     * @param userCommand Command input by the user.
     * @param userParams Description and date of task.
     * @param taskList Current session's TaskList.
     * @param storage Current session's Storage.
     * @throws InvalidDateFormatException If date is not formatted properly.
     * @throws NoDescException If description is missing.
     * @throws NoDateException If date is missing.
     */
    private static void parseDateTask(String userCommand, String userParams,
                                      TaskList taskList, Storage storage)
            throws InvalidDateFormatException, NoDescException, NoDateException {
        int delimIndex = userParams.indexOf("/"); // duke.Duke uses / to define where the date starts

        // If String.indexOf returns -1, the character has not been found
        if (delimIndex == -1) {
            throw new InvalidDateFormatException();
        }

        String desc = userParams.substring(0, delimIndex); // Get description substring (before /)
        String date = userParams.substring(delimIndex+3, userParams.length()); // Get date substring (after /at or /by)

        // Check that description and date exist
        if (desc.trim().isEmpty()) {
            throw new NoDescException();
        } else if (date.trim().isEmpty()) {
            throw new NoDateException();
        }

        LocalDate ld = LocalDate.parse(date.trim());

        if (userCommand.equals("deadline")) {
            taskList.addTask(new Deadline(desc.trim(), ld), storage);
        } else {
            taskList.addTask(new Event(desc.trim(), ld), storage);
        }
    }

    /**
     * Parses a Todo command.
     *
     * @param desc The description of the task.
     * @param taskList Current session's TaskList.
     * @param storage Current session's Storage.
     * @throws NoDescException If description is missing.
     */
    private static void parseTodo(String desc, TaskList taskList, Storage storage) throws NoDescException {
        // Check that description exists
        if (desc.trim().isEmpty()) {
            throw new NoDescException();
        }
        taskList.addTask(new Todo(desc.trim()), storage);
    }

    private static void parseFind(String userParams, TaskList taskList) throws NoFindException {
        if (userParams.trim().isEmpty()) {
            throw new NoFindException();
        }
        taskList.find(userParams);
    }
}
