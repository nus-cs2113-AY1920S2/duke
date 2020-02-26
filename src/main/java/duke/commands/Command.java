package duke.commands;

import duke.data.exception.DukeException;
import duke.data.task.TaskList;

/**
 * Used for execution of commands.
 * Contains both the information related to user commands and methods to execute them.
 * Implementation is done on child classes.
 */
public abstract class Command {

    /** Contain boolean to check for exit condition */
    protected boolean isExit;

    /**
     * Constructor for Command Class.
     * Set isExit to false as default
     */
    protected Command() {
        isExit = false;
    }

    /**
     * Executes the command based on the information provided by the user.
     *
     * @param tasklist Contains the list of tasks on which the commands are executed on.
     * @throws DukeException If issues are found during execution of command.
     */
    public void execute(TaskList tasklist) throws DukeException {
    }

    /**
     * Returns exit condition.
     *
     * @return isExit Contains information that allow the program to exit.
     */
    public boolean isExit() {
        return isExit;
    }
}
