package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Extension of <code>Command</code> class specifying the <code>List</code> command.
 */
public class List extends Command {

    public List() {
        super();
    }

    /**
     * Executes tasks for List command.
     *
     * @param tasks Tasks that will be augmented.
     * @param ui Messages that will be displayed.
     * @param storage Storage to be added into.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks.list);
    }


    /**
     * @return False, since this is not a "bye" command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
