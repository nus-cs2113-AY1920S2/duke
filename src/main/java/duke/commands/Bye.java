package duke.commands;

import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Extension of <code>Command</code> class specifying the <code>Bye</code> command.
 */
public class Bye extends Command {

    /**
     * Executes tasks for Bye command.
     *
     * @param tasks Tasks that will be augmented.
     * @param ui Messages that will be displayed.
     * @param storage Storage to be added into.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }


    /**
     * @return true, since this command would mean to exit the program.
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
