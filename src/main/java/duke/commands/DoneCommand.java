package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import static duke.common.Messages.COMPLETE_MESSAGE;

/**
 * Marks task at specified index as done.
 */
public class DoneCommand extends Command {

    /** Index of task to be marked as done */
    private static int index;

    /** Format of the command */
    public static final String COMMAND_PHRASE = "done (index)";

    /** Usage of the command */
    public static final String COMMAND_USAGE = COMMAND_PHRASE
            + System.lineSeparator() + "-Marks task at specified index as done";

    public DoneCommand(int index) {
        super();
        this.index = index;
    }

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
        index -= 1;
        tasks.markTask(index);
        String feedback = COMPLETE_MESSAGE + System.lineSeparator() + tasks.getIndex(index).toString();
        storage.saveChange(textUi,tasks);
        return new CommandResult(feedback);
    }
}