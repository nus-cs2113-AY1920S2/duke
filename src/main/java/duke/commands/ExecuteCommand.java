package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.exceptions.DukeException;

/**
 * Abstract class of all valid commands.
 */
public abstract class ExecuteCommand {
    protected boolean toExit;
    protected String userData;

    /**
     * Parse user input and adds Event tasks in TaskList object.
     * Stores existing TaskList object into output file.
     * @param tasks TaskList class object that handles all ArrayList of Tasks commands.
     * @param storage Storage class object that loads and saves data.
     * @throws DukeException If user input contains invalid command.
     */
    public abstract void execute(TaskList tasks, Storage storage) throws DukeException;

    /**
     * @return True if exit command is given by user.
     */
    public boolean toExit() {
        return toExit;
    }
}
