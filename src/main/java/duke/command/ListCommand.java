package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Handles the task of displaying the list of tasks.
 */
public class ListCommand extends Command {

    /** Denotes whether the command contains a single word */
    private boolean isOneWordCommand;

    /**
     * Constructor for ListCommand Class.
     * It creates a new ListCommand Object.
     *
     * @param isOneWordCommand Denotes whether the command contains a single word.
     */
    public ListCommand(boolean isOneWordCommand) {
        this.commandType = CommandType.ListCommand;
        this.isOneWordCommand = isOneWordCommand;
    }

    /**
     * Prints the current list of tasks by calling {@link TaskList#listTasks(boolean)}
     *
     * @param taskList Contains the list of tasks on which the commands are executed on.
     * @throws DukeException If the wrong format is used for list command.
     * @see TaskList#listTasks(boolean)
     */
    @Override
    public void executeCommand(TaskList taskList) throws DukeException {
        taskList.listTasks(isOneWordCommand);
    }
}
