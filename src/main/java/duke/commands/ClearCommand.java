package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class ClearCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int listSize = tasks.getListSize();
        // always delete the first tasks until there is no task
        for (int i = 1; i <= listSize; i++) {
            tasks.deleteTask(1);
        }
        
        ui.displayClearAllMessage();

        // update the file
        try {
            storage.updateTasksToFile(tasks);
        } catch (IOException e) {
            ui.displayErrorMessage(e);
        }
    }
}
