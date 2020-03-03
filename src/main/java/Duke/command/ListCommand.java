package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Class representing a command to display existing task.
 */
public class ListCommand extends Command {
    /**
     * Executes this command on the given task list and user interface.
     *
     * @param ui The user interface displaying events on the task list.
     * @param storage The duke.storage object containing task list.
     */
    @Override
    public void execute(Ui ui, Storage storage) throws DukeException {
        Ui.displayTaskList(storage.getTasks());
    }
}
