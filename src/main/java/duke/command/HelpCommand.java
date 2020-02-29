package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Deals with command that shows help message.
 */
public class HelpCommand extends Command {
    public HelpCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showHelpMessage();
    }
}
