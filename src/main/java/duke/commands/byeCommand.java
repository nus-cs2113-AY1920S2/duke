package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

public class byeCommand extends Command {

    public byeCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList tasklist, UI ui, Storage storage) {
        ui.displayGoodbyeMessage();
        ui.setExitStatus(true);
    }
    @Override
    public String toString() {
        return null;
    }
}
