package Duke.Commands;

import Duke.Exception.DukeException;
import Duke.Library.ErrorMessage;
import Duke.Storage.Storage;
import Duke.Task.Task;
import Duke.Ui.Ui;

/**
 * Class representing a command to delete an existing task.
 */
public class DeleteCommand extends Command {

    private int index;
    /**
     * Creates a new DeleteCommand with the given task.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes this command on the given task list and user interface.
     *
     * @param ui The user interface displaying events on the task list.
     * @param storage The duke.storage object containing task list.
     */
    @Override
    public void execute(Ui ui, Storage storage) throws DukeException {
        try {
            Task task = storage.getTasks().remove(index);
            Ui.displayDeletedTask(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ErrorMessage.OUT_OF_BOUNDS);
        }
        storage.write();
    }
}
