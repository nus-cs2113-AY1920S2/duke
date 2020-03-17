package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Extension of <>Command</> class specifying the <>Find</> command.
 */
public class Find extends Command {
    private String target;

    public Find (String target) {
        this.target = target;
    }

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
