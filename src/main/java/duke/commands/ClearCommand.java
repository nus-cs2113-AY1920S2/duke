package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class ClearCommand extends Command {
    /**
     * Executes command "clear".
     * Removes all the tasks out of the existing tasks list, i.e. empties the task list.
     * Updates txt file whenever the task list changes.
     *
     * @param tasks Task list that stores all the existing tasks.
     * @param ui Interaction with users.
     * @param storage Files related operation object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int listSize = tasks.getListSize();
        // always delete the first tasks until there is no task
        for (int i = 1; i <= listSize; i++) {
            tasks.deleteTask(1);
        }
        
        ui.displayClearAllMessage();

        // update the txt file
        try {
            storage.updateTasksToFile(tasks);
        } catch (IOException e) {
            ui.displayErrorMessage(e);
        }
    }
}
