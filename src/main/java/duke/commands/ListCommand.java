package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

public class ListCommand extends Command {

    public ListCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList tasklist, UI ui, Storage storage) {
        ui.displayShowListMessage(tasklist.getTaskList());

    }
}
