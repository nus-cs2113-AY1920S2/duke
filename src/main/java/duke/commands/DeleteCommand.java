package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.ui.UI;

/**
 * DeleteCommand inherits from command and is the public class responsible for storing information and executing the command.
 */

public class DeleteCommand extends Command {

    /**
     * The index of the task to be deleted entered by the user.
     */

    protected String index;

    /**
     * Constructs the DeleteCommand object.
     *
     * @param command the command prompt entered by the user.
     * @param index   the index of the task to be deleted entered by the user.
     */

    public DeleteCommand(String command, String index) {
        super(command);
        this.index = index;
    }

    /**
     * Executes the DeleteCommand and deletes the task from the tasklist.
     *
     * @param tasklist the list containing all current tasks.
     * @param ui       the object containing user interface functions.
     * @param storage  the object containing storage functions.
     */

    @Override
    public void execute(TaskList tasklist, UI ui, Storage storage) {
        int taskIndex = Integer.parseInt(this.index) - 1;
        if (taskIndex >= tasklist.getLength()) throw new IndexOutOfBoundsException();
        Task deleteTask = tasklist.getTaskList().get(taskIndex);
        tasklist.removeTask(deleteTask);
        storage.rewriteFile();
        ui.displayDeleteTaskMessage(deleteTask);
    }
}
