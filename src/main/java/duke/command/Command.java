package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Used for execution of commands.
 * Contains both the information related to user commands and methods to execute them.
 * Implementation is done on child classes.
 */
public abstract class Command {

    /** Specifies the type of command */
    protected CommandType commandType;

    /**
     * Executes the command based on the information provided by the user.
     *
     * @param taskList Contains the list of tasks on which the commands are executed on.
     * @throws DukeException If issues are found during execution of command.
     */
    public abstract void executeCommand(TaskList taskList) throws DukeException;
}
