package Duke.Commands;

import Duke.Exception.DukeException;
import Duke.Storage.Storage;
import Duke.Ui.Ui;

/**
 * Abstract class for all commands
 */
public abstract class Command {
    /**
     * Executes this command on the given task list and user interface.
     *
     * @param ui The user interface displaying events on the task list.
     * @param storage The duke.storage object containing task list.
     */
    public abstract void execute(Ui ui, Storage storage) throws DukeException;
}

