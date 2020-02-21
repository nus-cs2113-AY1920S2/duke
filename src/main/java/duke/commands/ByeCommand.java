package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import static duke.common.Messages.BYE_MESSAGE;

/**
 * Displays the bye message to the user before closing the application.
 */
public class ByeCommand extends Command {

    /** Format of the command */
    public static final String COMMAND_PHRASE = "bye";

    /** Usage of the command */
    public static final String COMMAND_USAGE = COMMAND_PHRASE
            + System.lineSeparator() + "-Exits the application";

    /**
     * Returns a <code>CommandResult</code> with feedback to the user initialised.
     *
     * @param tasks <code>TaskList</code> object that the command will execute on.
     * @param textUi <code>Ui</code> object that is being used to display output to the user.
     * @param storage <code>Storage</code> object that is able to access tasks saved in memory.
     * @return <code>CommandResult</code>.
     */
    @Override
    public CommandResult execute(TaskList tasks, Ui textUi, Storage storage) {
        this.isExit = true;
        return new CommandResult(BYE_MESSAGE);
    }
}
