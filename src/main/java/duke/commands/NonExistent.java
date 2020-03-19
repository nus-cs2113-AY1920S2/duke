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
     * @param tasks     the tasks that will be augmented
     * @param ui        the messages that will be displayed
     * @param storage   the storage to be added into
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.showNonExistentInputError();
    }

    /**
     * @return false, since this is not a "bye" command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
