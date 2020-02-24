package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.exception.ExceptionType;
import duke.task.TaskType;
import duke.ui.Ui;


public class Parser {

    public static final String LIST_COMMAND = "list";
    public static final String EMPTY_COMMAND = "";
    public static final String DONE_COMMAND = "done";
    public static final String DELETE_COMMAND = "delete";
    public static final String ADD_TODO_COMMAND = "todo";
    public static final String ADD_DEADLINE_COMMAND = "deadline";
    public static final String ADD_EVENT_COMMAND = "event";

    // Executes the command entered by the user
    public Command parseCommand(String fullCommand) throws DukeException {
        String[] commandSplit = fullCommand.split(" ",2);
        String commandType = commandSplit[0];
        boolean isOneWordCommand = commandSplit.length == 1 || commandSplit[1].isBlank();
        switch (commandType) {
        case ADD_TODO_COMMAND:
            return new AddCommand(commandSplit,isOneWordCommand, TaskType.ToDo);
            // break statement can't be reached if added
        case ADD_DEADLINE_COMMAND:
            return new AddCommand(commandSplit,isOneWordCommand, TaskType.Deadline);
            // break statement can't be reached if added
        case ADD_EVENT_COMMAND:
            return new AddCommand(commandSplit,isOneWordCommand, TaskType.Event);
            // break statement can't be reached if added
        case LIST_COMMAND:
            return new ListCommand(isOneWordCommand);
            // break statement can't be reached if added
        case DONE_COMMAND:
            return new DoneCommand(commandSplit);
            // break statement can't be reached if added
        case DELETE_COMMAND:
            return new DeleteCommand(commandSplit);
            // break statement can't be reached if added
        case EMPTY_COMMAND:
            throw new DukeException(ExceptionType.EmptyCommand);
            // break statement can't be reached if added
        default:
            throw new DukeException(ExceptionType.InvalidCommand);
            // break statement can't be reached if added
        }
    }
}
