package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Storage;

public class ListCommand implements Command {
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showList(taskList.getList());
    }
}
