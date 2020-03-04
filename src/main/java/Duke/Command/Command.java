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
     * @param tasks object of TaskList class containing list of tasks in the program.
     * @param ui object of Ui class handling printing output to the user.
     * @param storage object of Storage class for saving program to file.
     * @throws DukeException if error specific to Duke program.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Abstract method that indicates whether program ready to exit.
     */
    public abstract boolean isExit();

}
