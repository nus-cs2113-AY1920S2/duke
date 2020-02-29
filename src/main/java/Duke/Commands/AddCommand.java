package Duke.Commands;

import Duke.Task.Task;
import Duke.Exception.DukeException;
import Duke.Storage.Storage;
import Duke.Ui.Ui;


/**
 *
 */
public class AddCommand extends Command {

    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(Ui ui, Storage storage) throws DukeException {
        storage.getTasks().add(task);
        Ui.displayAddTask(task);
        storage.write();
    }
}
