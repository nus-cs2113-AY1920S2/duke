package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import static duke.common.Messages.DELETE_MESSAGE;

/**
 * Deletes a task at the specified index.
 */
public class DeleteCommand extends Command {

    /** Index of the task to be removed */
    private static int index;

    /** Format of the command */
    public static final String COMMAND_PHRASE = "delete (index)";

    /** Usage of the command */
    public static final String COMMAND_USAGE = COMMAND_PHRASE
            + System.lineSeparator() + "-Deletes the task at the specified index";

    public DeleteCommand(int index) {
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
        String feedback = DELETE_MESSAGE + System.lineSeparator() + tasks.getIndex(index)
                + System.lineSeparator() + "Now you have " + (tasks.getSize()-1) + " tasks in the list.";
        tasks.removeTask(index);
        storage.saveChange(textUi,tasks);
        return new CommandResult(feedback);
    }
}
