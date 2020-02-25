package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.exception.ExceptionType;
import duke.task.TaskType;

/**
 * Used to make sense of the user command.
 */
public class Parser {

    public static final String LIST_COMMAND = "list";
    public static final String EMPTY_COMMAND = "";
    public static final String DONE_COMMAND = "done";
    public static final String DELETE_COMMAND = "delete";
    public static final String ADD_TODO_COMMAND = "todo";
    public static final String ADD_DEADLINE_COMMAND = "deadline";
    public static final String ADD_EVENT_COMMAND = "event";

    /**
     * Makes sense of the command entered by the user and returns the corresponding Command Object.
     *
     * @param fullCommand Contains the entire command entered by the user as a String.
     * @return command Contains information required for the execution of the command.
     * @throws DukeException If the command issued doesn't follow the required format.
     */
    public Command parseCommand(String fullCommand) throws DukeException {
        String[] commandSplit = fullCommand.split(" ",2);
        String commandType = commandSplit[0];
        Command command;
        boolean isOneWordCommand = commandSplit.length == 1 || commandSplit[1].isBlank();
        switch (commandType) {
        case ADD_TODO_COMMAND:
            command = new AddCommand(commandSplit,isOneWordCommand, TaskType.ToDo);
            break;
        case ADD_DEADLINE_COMMAND:
            command = new AddCommand(commandSplit,isOneWordCommand, TaskType.Deadline);
            break;
        case ADD_EVENT_COMMAND:
            command = new AddCommand(commandSplit,isOneWordCommand, TaskType.Event);
            break;
        case LIST_COMMAND:
            command = new ListCommand(isOneWordCommand);
            break;
        case DONE_COMMAND:
            command = new DoneCommand(commandSplit);
            break;
        case DELETE_COMMAND:
            command = new DeleteCommand(commandSplit);
            break;
        case EMPTY_COMMAND:
            throw new DukeException(ExceptionType.EmptyCommandException);
            // break statement can't be reached if added
        default:
            throw new DukeException(ExceptionType.InvalidCommandException);
            // break statement can't be reached if added
        }
        return command;
    }
}
