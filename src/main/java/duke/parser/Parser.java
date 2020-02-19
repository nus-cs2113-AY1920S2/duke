package duke.parser;

import duke.exception.InvalidCommandException;
import duke.exception.InvalidDateFormatException;
import duke.exception.NoDateException;
import duke.exception.NoDescException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

import java.time.LocalDate;
import java.util.Scanner;

public class Parser {
    Scanner inputScanner;

    public Parser() {
        inputScanner = new Scanner(System.in);
    }

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

    public static void parseCommand(String userCommand, String userParams,
                                    TaskList taskList, Storage storage)
            throws NoDescException, NoDateException, InvalidDateFormatException, InvalidCommandException {
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

    private static void parseTodo(String userParams, TaskList taskList, Storage storage) throws NoDescException {
        // Check that description exists
        if (userParams.trim().isEmpty()) {
            throw new NoDescException();
        }
        taskList.addTask(new Todo(userParams.trim()), storage);
    }

}
