package chatty.command;

import chatty.storage.Storage;
import chatty.task.Task;
import chatty.task.TaskList;
import chatty.ui.Ui;

/**
 * Delete command used in the application.
 */
public class DeleteCommand extends Command {

    private int idx;

    /**
     * Constructor for delete command.
     *
     * @param idx Index in the delete command.
     */
    public DeleteCommand(int idx) {
        super();
        this.idx = idx;
    }

    /**
     * Gets index from the delete command.
     *
     * @return The index in the delete command.
     */
    public int getIdx() {
        return idx;
    }

    /**
     * Executes the delete command.
     *
     * @param taskList Task list containing all tasks.
     * @param ui       UI to handle sending message to users.
     * @param storage  Storage to handle reading and writing of tasks from disk.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task deletedTask = taskList.deleteTask(this.getIdx());
        ui.sendTaskDeletedMessage(deletedTask);
    }
}
