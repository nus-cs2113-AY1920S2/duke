package duke.command;

import duke.TaskList;
import duke.exception.DukeException;
import duke.Storage;

/**
 * Abstract class representing the commands recognised by Duke.
 */
public abstract class Command {

    protected String userInput;
    protected boolean isExit;

    /**
     * Executes commands based on input to Duke.
     * @param tasks TaskList class object which handles operation involving ArrayList of Tasks
     * @param storage Storage class object which manages storing and loading of data
     * @throws DukeException If there are errors with the input or errors when executing the command
     */
    abstract public void execute(TaskList tasks, Storage storage) throws DukeException;

    /**
     * Returns the exit signal of the program.
     * @return True if exit command is given
     */
    public boolean isExit() {
        return isExit;
    }


}
