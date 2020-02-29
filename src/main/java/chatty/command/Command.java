package chatty.command;

import chatty.storage.Storage;
import chatty.task.TaskList;
import chatty.ui.Ui;

/**
 * Command used in the application.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param taskList Task list containing all tasks.
     * @param ui       UI to handle sending message to users.
     * @param storage  Storage to handle reading and writing of tasks from disk.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Checks whether the command is a bye command.
     *
     * @return Boolean value indicating whether or not the command is a bye command.
     */
    public boolean isBye() {
        return false;
    }
}
