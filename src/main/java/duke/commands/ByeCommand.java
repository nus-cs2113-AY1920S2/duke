package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

public class ByeCommand extends Command {

    public ByeCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList tasklist, UI ui, Storage storage) {
        ui.displayGoodbyeMessage();
        ui.setExitStatus(true);
    }
}
