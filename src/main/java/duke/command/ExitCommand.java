package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Deals with command related to exit the Duke.
 */
public class ExitCommand extends Command {
    public ExitCommand() {

    }

    @Override
    public Boolean isExit() {
        return true;
    }

    /**
     * Updates ui to show Goodbye.
     *
     * @param tasks Stores all tasks, useless here.
     * @param ui Deals with user interface.
     * @param storage Deals with back up file, useless here.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodByeMessage();
    }
}
