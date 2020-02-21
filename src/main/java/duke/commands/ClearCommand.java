package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import static duke.common.Messages.CLEAR_MESSAGE;

/**
 * Clears the list of tasks.
 */
public class ClearCommand extends Command {

    /** Format of the command */
    public static final String COMMAND_PHRASE = "clear";

    /** Usage of the command */
    public static final String COMMAND_USAGE = COMMAND_PHRASE
            + System.lineSeparator() + "-Clears all tasks in the list";

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
        String feedback = CLEAR_MESSAGE;
        tasks.clearList();
        storage.performCleanup(textUi);
        return new CommandResult(feedback);
    }
}
