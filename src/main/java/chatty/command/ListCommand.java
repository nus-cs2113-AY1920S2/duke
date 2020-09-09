package chatty.command;

import chatty.storage.Storage;
import chatty.task.TaskList;
import chatty.ui.Ui;

/**
 * List command used in the application.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command.
     *
     * @param taskList Task list containing all tasks.
     * @param ui       UI to handle sending message to users.
     * @param storage  Storage to handle reading and writing of tasks from disk.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.listAllTasks(taskList);
    }
}
