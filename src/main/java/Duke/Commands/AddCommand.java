package Duke.Commands;

import Duke.Task.Task;
import Duke.Exception.DukeException;
import Duke.Storage.Storage;
import Duke.Ui.Ui;


/**
 * Class representing a command to add a new task.
 * Types of task that can be created - Todo, Deadline, Event
 */
public class AddCommand extends Command {

    private final Task task;

    /**
     * Creates a new AddCommand with the given task.
     *
     * @param task The task to add.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes this command on the given task list and user interface.
     *
     * @param ui The user interface displaying events on the task list.
     * @param storage The duke.storage object containing task list.
     */
    @Override
    public void execute(Ui ui, Storage storage) throws DukeException {
        storage.getTasks().add(task);
<<<<<<< HEAD
        ui.displayAddedTask(task);
=======
        Ui.displayAddTask(task);
>>>>>>> branch-A-JavaDoc
        storage.write();
    }
}
