package duke.command;

import duke.exception.DukeException;
import duke.library.ErrorMessage;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Class representing a command to mark an existing task.
 */
public class MarkDoneCommand extends Command {

    private int index;

    /**
     * Creates a new MarkDoneCommand with the given task.
     *
     * @param index The index of the task to be marked.
     */

    public MarkDoneCommand(int index) {
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
            Task task = storage.getTasks().get(index);
            if (!task.isDone()) {
                task.setDone(true);
                ui.displayMarkDone(task);
            } else {
                ui.displayDone(task);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ErrorMessage.OUT_OF_BOUNDS);
        }
        storage.write();
    }
}
