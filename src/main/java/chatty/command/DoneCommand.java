package chatty.command;

import chatty.storage.Storage;
import chatty.task.Task;
import chatty.task.TaskList;
import chatty.ui.Ui;

/**
 * Done command used in the application.
 */
public class DoneCommand extends Command {

    private int idx;

    /**
     * Constructor for done command.
     *
     * @param idx Index in the done command.
     */
    public DoneCommand(int idx) {
        super();
        this.idx = idx;
    }

    /**
     * Gets index from the done command.
     *
     * @return The index in the done command.
     */
    public int getIdx() {
        return idx;
    }

    /**
     * Executes the done command.
     *
     * @param taskList Task list containing all tasks.
     * @param ui       UI to handle sending message to users.
     * @param storage  Storage to handle reading and writing of tasks from disk.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task doneTask = taskList.markTaskAsDone(this.getIdx());
        ui.sendTaskDoneMessage(doneTask);
    }
}
