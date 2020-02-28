package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.HelpList;
import duke.ui.UI;

public class HelpCommand extends Command {

    protected String description;

    public HelpCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList tasklist, UI ui, Storage storage) {
        ui.displayHelpListMessage();
    }
}
