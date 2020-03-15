package duke;

import duke.command.*;
import duke.exception.DukeArgumentException;
import duke.exception.DukeIndexException;
import duke.exception.DukeNullException;
import duke.task.Task;

import java.util.ArrayList;

/**
 * The Parser class is in charged of parsing user input into a Command Object.
 * @author Lam Yue Wei
 * @version CS2113 AY19/20 Sem 2 Duke
 */
public class Parser {

    /**
     * Empty constructor for Parser.
     */
    public Parser() {
    }

    /**
     * Parse the user input into a Command Object based on the command keywords.
     * @param userCommand User input.
     * @return Command Object if command keyword, description and date is valid or DukeNullException otherwise.
     */
    public Command parseCommand(String userCommand) throws DukeArgumentException, DukeNullException, DukeIndexException {
        String commandKeyWord = getCommandKeyWord(userCommand);
        switch (commandKeyWord) {
        case "todo":
            return parseTodoCommand(userCommand);
        case "list":
            return parseListCommand(userCommand);
        case "done":
            return parseDoneCommand(userCommand);
        case "delete":
            return parseDeleteCommand(userCommand);
        case "find":
            return parseFindCommand(userCommand);
        case "help":
            return parseHelpCommand(userCommand);

        /*case "deadline":
        case "event":

        case "bye":
            break;*/
        default:
            throw new DukeNullException("     :( OOPS!!! Command does not exist.");
        }
    }

    /**
     * Extract and return the command keyword from the user input.
     * @param userCommand User input.
     * @return String representation of Command keyword.
     */
    public String getCommandKeyWord(String userCommand) {
        String[] userCommandSplit = userCommand.split(" ");
        return userCommandSplit[0];
    }

    /**
     * Extract and return the description from the user input.
     * @param userCommand User input.
     * @return String representation of the description for the command to process.
     */
    public String getDescription(String userCommand) {
        String userCommandKeyWord = getCommandKeyWord(userCommand);
        if (userCommand.equals("deadline")) {
            return "To be edited in parser class";
        } else if (userCommand.equals("event")) {
            return "Edit Parser class";
        }
        return userCommand.substring(userCommandKeyWord.length()).strip();
    }

    /**
     * Parse userCommand as TodoCommand that add todo Task given with a description.
     * @param userCommand User input.
     * @return TodoCommand which adds Todo Task into the TaskList.
     * @throws DukeArgumentException If missing parameter for index.
     */
    public TodoCommand parseTodoCommand(String userCommand) throws DukeArgumentException {
        String description = getDescription(userCommand);
        if (description.length() == 0) {
            throw new DukeArgumentException("     :( OOPS!!! Missing description for todo.");
        }
        return new TodoCommand(description);
    }

    /**
     * Parse userCommand as ListCommand that list all the Task stored.
     * @param userCommand User input.
     * @return ListCommand Object that provides the stored tasks.
     * @throws DukeArgumentException If additional parameter is provided.
     */
    public ListCommand parseListCommand(String userCommand) throws DukeArgumentException {
        if (getDescription(userCommand).length() > 0) {
            throw new DukeArgumentException("     :( OOPS!!! Description not required for list.");
        }
        return new ListCommand();
    }

    /**
     * Parse userCommand as DoneCommand that marks a Task as done.
     * @param userCommand User input.
     * @return DoneCommand Object that marks a Task as done.
     * @throws DukeArgumentException If missing parameter for index.
     * @throws DukeIndexException If index provided has incorrect format.
     */
    public DoneCommand parseDoneCommand(String userCommand) throws DukeArgumentException, DukeIndexException {
        if (getDescription(userCommand).length() == 0) {
            throw new DukeArgumentException("     :( OOPS!!! Missing index for done.");
        }

        try {
            int doneTask = Integer.parseInt(getDescription(userCommand)) - 1; // Might throw NumberFormatException
            return new DoneCommand(doneTask);
        } catch (NumberFormatException e) {
            throw new DukeIndexException("     :( OOPS!!! " + e.getMessage().substring(18) + " is not number!");
        }
    }

    /**
     * Parse userCommand as DeleteCommand that delete a Task.
     * @param userCommand User input.
     * @return DeleteCommand Object that delete a Task from the TaskList.
     * @throws DukeArgumentException If missing parameter for index.
     * @throws DukeIndexException If index provided has incorrect format.
     */
    public DeleteCommand parseDeleteCommand(String userCommand) throws DukeArgumentException, DukeIndexException {
        if (getDescription(userCommand).length() == 0) {
            throw new DukeArgumentException("     :( OOPS!!! Missing index for delete.");
        }
        try {
            int deleteTask = Integer.parseInt(getDescription(userCommand)) - 1; // Might throw NumberFormatException
            return new DeleteCommand(deleteTask);
        } catch (NumberFormatException e) {
            throw new DukeIndexException("     :( OOPS!!! " + e.getMessage().substring(18) + " is not number!");
        }
    }

    /**
     * Parse userCommand as FindCommand that find all Task with description that matches a key word or phrase.
     * @param userCommand User input.
     * @return FindCommand Object that find all Task with description that matches a key word or phrase.
     * @throws DukeArgumentException If missing parameter for description.
     */
    public FindCommand parseFindCommand(String userCommand) throws DukeArgumentException {
        if (userCommand.length() == 4) {
            throw new DukeArgumentException("     :( OOPS!!! Missing description for find.");
        }
        return new FindCommand(getDescription(userCommand));
    }

    /**
     * Parse userCommand as HelpCommand that show the list of available command.
     * @param userCommand User input.
     * @return HelpCommand Object that show the list of available command.
     * @throws DukeArgumentException If additional parameter is provided.
     */
    public HelpCommand parseHelpCommand(String userCommand) throws DukeArgumentException {
        if (getDescription(userCommand).length() > 0) {
            throw new DukeArgumentException("     :( OOPS!!! Description not required for help.");
        }
        return new HelpCommand();
    }
}