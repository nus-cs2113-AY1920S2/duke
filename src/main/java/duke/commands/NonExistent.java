package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Extension of <code>Command</code> class to handle non-existing classes.
 */
public class NonExistent extends Command {
    public NonExistent() {
    }

    /**
     * Executes tasks for Non-existing command.
     *
     * @param tasks Tasks that will be augmented.
     * @param ui Messages that will be displayed.
     * @param storage Storage to be added into.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.showNonExistentInputError();
    }

    /**
     * @return False, since this is not a "bye" command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
