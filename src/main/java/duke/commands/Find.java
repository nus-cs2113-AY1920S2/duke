package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Extension of <code>Command</code> class specifying the <code>Find</code> command.
 */
public class Find extends Command {
    private String target;

    public Find(String target) {
        this.target = target;
    }

    /**
     * Executes tasks for Find command.
     *
     * @param tasks Tasks that will be augmented.
     * @param ui Messages that will be displayed.
     * @param storage Storage to be added into.
     * @throws DukeException Relays exceptions from methods.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (target.matches("\\s*")) {
            throw new DukeException("find", 1);
        }
        ui.showFindOutput(target);
    }

    @Override
    public boolean isExit() {
        return false;
    }


}
