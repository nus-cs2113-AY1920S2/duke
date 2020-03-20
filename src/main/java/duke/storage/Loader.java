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

    /**
     * Executes tasks for loading.
     *
     * @param tasks Tasks that will be augmented.
     * @param ui Messages that will be displayed.
     * @param storage Storage to be added into.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
