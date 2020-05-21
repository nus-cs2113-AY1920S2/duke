package commands;
import exception.DukeException;
import task.TaskList;
import ui.Ui;
import storage.Storage;

/**
 * Parent command class for the other commands.
 */
public class Command {

    /** Executes the command. */
    public void execute(TaskList task, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("This method is to be implemented by child classes");
    }

    /** Check if it is an exit command.
     *
     * @return true or false depending on whether it is an exit command
     */
    public boolean isExit() {
        return false;
    }
}
