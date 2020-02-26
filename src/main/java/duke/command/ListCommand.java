package duke.command;

import duke.common.DukeException;
import duke.taskList.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("\tCurrently, there is no task in the list!");
        }
        ui.showListOfTasks(tasks);
    }
}
