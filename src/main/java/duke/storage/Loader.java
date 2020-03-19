package duke.storage;

import duke.commands.Command;
import duke.commands.TaskList;
import duke.ui.Ui;

/**
 * Loader class to allow easy usage of data file.
 */
public class Loader extends Command {
    public Loader(String pastListItem) {
        super(pastListItem);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
