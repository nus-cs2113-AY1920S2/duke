package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Command class for the Exit command.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        ui.displayExit();
    }
}
