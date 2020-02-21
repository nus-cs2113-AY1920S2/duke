package duke.parser;

import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddTodoCommand;
import duke.commands.ByeCommand;
import duke.commands.ClearCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.FindCommand;
import duke.commands.HelpCommand;
import duke.commands.ListCommand;
import duke.exception.FormatErrorException;
import duke.exception.IncompleteInputException;
import duke.exception.InvalidInputException;
import duke.exception.OutOfBoundsException;
import duke.task.TaskList;

/**
 * Parses user input.
 */
public class Parser {

    /** Resulting command after parsing */
    private static Command newCommand;

    /** Individual strings of the command after it is split by spaces */
    private static String[] phrases;

    /** First word in the command string */
    private static String commandWord;

    /** Index supplied by user if any */
    private static int index;

    /**
     * Parses the user command supplied for execution.
     *
     * @param command Command supplied by user.
     * @param tasks Current task list.
     * @return <code>Command</code> object obtained after parsing.
     * @throws InvalidInputException
     * @throws IncompleteInputException
     * @throws FormatErrorException
     * @throws OutOfBoundsException
     */
    public static Command parseCommand(String command, TaskList tasks) throws InvalidInputException,
            IncompleteInputException, FormatErrorException, OutOfBoundsException {
        prepareCommand(command);
        switch(commandWord) {
        case "bye":
            createByeCommand();
            break;
        case "list":
            createListCommand();
            break;
        case "done":
            checkCompleteInput();
            checkWithinBounds(tasks);
            createDoneCommand();
            break;
        case "deadline":
            checkCompleteInput();
            createDeadlineCommand(command);
            break;
        case "event":
            checkCompleteInput();
            createEventCommand(command);
            break;
        case "todo":
            checkCompleteInput();
            createTodoCommand(command);
            break;
        case "delete":
            checkCompleteInput();
            checkWithinBounds(tasks);
            createDeleteCommand();
            break;
        case "clear":
            createClearCommand();
            break;
        case "help":
            createHelpCommand();
            break;
        case "find":
            createFindCommand();
            break;
        default:
            throw new InvalidInputException();
        }
        return newCommand;
    }

    /**
     * Initialises the FindCommand.
     */
    private static void createFindCommand() {
        if (phrases.length == 1) {
            newCommand = new ListCommand();
        } else {
            newCommand = new FindCommand(phrases[1]);
        }
    }

    /**
     * Initialises the HelpCommand.
     */
    private static void createHelpCommand() {
        newCommand = new HelpCommand();
    }

    /**
     * Initialises the ClearCommand.
     */
    private static void createClearCommand() {
        newCommand = new ClearCommand();
    }

    /**
     * Initialises the DeleteCommand.
     */
    private static void createDeleteCommand() {
        newCommand = new DeleteCommand(index);
    }

    /**
     * Initialises the AddTodoCommand.
     *
     * @param command Command supplied by the user.
     */
    private static void createTodoCommand(String command) {
        String description = command.substring(phrases[0].length()+1);
        newCommand = new AddTodoCommand(description);
    }

    /**
     * Initialises the AddEventCommand.
     *
     * @param command Command supplied by the user.
     * @throws FormatErrorException
     */
    private static void createEventCommand(String command) throws FormatErrorException {
        int pos = command.indexOf("/at");
        if (pos == -1) {
            throw new FormatErrorException();
        }
        String description = command.substring(phrases[0].length()+1,pos-1);
        String duration = command.substring(pos+4);
        newCommand = new AddEventCommand(description,duration);
    }

    /**
     * Initialises the AddDeadlineCommand.
     *
     * @param command Command supplied by the user.
     * @throws FormatErrorException
     */
    private static void createDeadlineCommand(String command) throws FormatErrorException {
        int position = command.indexOf("/by");
        if (position == -1) {
            throw new FormatErrorException();
        }
        String description = command.substring(phrases[0].length()+1,position-1);
        String by = command.substring(position+4);
        newCommand = new AddDeadlineCommand(description,by);
    }

    /**
     * Initialises the DoneCommand with the user-specified index.
     */
    private static void createDoneCommand() {
        newCommand = new DoneCommand(index);
    }

    /**
     * Checks if index supplied by user is within bounds.
     *
     * @param tasks Current list of tasks.
     * @throws OutOfBoundsException
     */
    private static void checkWithinBounds(TaskList tasks) throws OutOfBoundsException {
        index = Integer.parseInt(phrases[1]);
        if ((index > tasks.getSize()) || (index < 1)) {
            throw new OutOfBoundsException();
        }
    }

    /**
     * Checks if user supplied the index if it is required by the command.
     *
     * @throws IncompleteInputException
     */
    private static void checkCompleteInput() throws IncompleteInputException {
        if (phrases.length == 1) {
            throw new IncompleteInputException();
        }
    }

    /**
     * Initialises the ListCommand.
     */
    private static void createListCommand() {
        newCommand = new ListCommand();
    }

    /**
     * Initialises the ByeCommand.
     */
    private static void createByeCommand() {
        newCommand = new ByeCommand();
    }

    /**
     * Extracts the command word from the user supplied command.
     *
     * @param command Command supplied by the user.
     * @throws InvalidInputException
     */
    public static void prepareCommand(String command) throws InvalidInputException {
        phrases = command.split(" ");
        if (phrases.length == 0) {
            throw new InvalidInputException();
        } else {
            commandWord = phrases[0];
        }
    }
}
