package chatty.command;

import chatty.storage.Storage;
import chatty.task.TaskList;
import chatty.ui.Ui;

/**
 * Bye command used in the application.
 */
public class ByeCommand extends Command {

    /**
     * Executes the bye command.
     *
     * @param taskList Task list containing all tasks.
     * @param ui       UI to handle sending message to users.
     * @param storage  Storage to handle reading and writing of tasks from disk.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (storage.saveDataToFile(taskList)) {
            ui.sendSaveTaskSuccessMessage();
        } else {
            ui.sendSaveTaskFailMessage();
        }
        ui.sendByeMessage();
    }

    /**
     * Checks whether the command is a bye command.
     *
     * @return Boolean value true value since this is a bye command.
     */
    @Override
    public boolean isBye() {
        return true;
    }
}
