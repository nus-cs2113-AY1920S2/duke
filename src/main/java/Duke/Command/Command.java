package Duke.Command;

import Duke.UI.Ui;
import Duke.TaskList;
import Duke.Storage;
import Duke.DukeException;

/**
 * Abstract class representing all the executable commands in the program.
 */
public abstract class Command {

    /**
     * Executes commands depending on input from user.
     *
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException if error specific to Duke program.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Abstract method that indicates whether program ready to exit.
     */
    public abstract boolean isExit();

}
