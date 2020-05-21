package commands;

import exception.DukeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Parent command class for the Add commands.
 */
public class AddCommand extends Command {

    AddCommand() {}

    /**
     * Executes the Add Command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("This method is to be implemented by child AddCommand classes");
    }
}
