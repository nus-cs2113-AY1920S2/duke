package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.HelpList;
import duke.ui.UI;

public class helpCommand extends Command {

    protected String description;

    public helpCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList tasklist, UI ui, Storage storage) {
        ui.displayHelpListMessage();
    }
}
