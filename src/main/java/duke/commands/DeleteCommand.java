package duke.commands;

import duke.data.TaskList;
import duke.ui.UI;

import static duke.exception.ExceptionMessages.INVALID_LIST_NUMBER_MESSAGE;
import static duke.ui.Messages.ABORT_DELETE_MESSAGE;
import static duke.ui.Messages.DELETE_TASK_CONFIRMATION_MESSAGE;
import static duke.ui.Messages.PROMPT_VALID_DELETE_CONFIRMATION_MESSAGE;
import static duke.ui.Messages.deleteTaskMessage;

/**
 * <h3>Delete Command</h3>
 * A <b>Command</b> to delete a <i>task</i> in the <b>TaskList</b> at the given index.
 * @see Command
 * @see TaskList
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String FORMAT = "delete <list number>";

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the <b>Delete Command</b> to to delete a <i>task</i> in the <b>TaskList</b> at the <code>index</code>.
     * <p></p>
     * <b>Note</b>: To avoid accidental deletions, a confirmation request will be prompted to the user whether to
     * continue to delete the <i>task</i>. If the user rejects, the <i>task</i> will not be deleted.
     *
     * @return The <b>Command Result</b> of the execution
     * @see TaskList
     * @see CommandResult
     */
    @Override
    public CommandResult execute() {
        try {
            String taskToDelete = TaskList.get(index).getTaskStatus();

            boolean canDelete = new UI().getConfirmation(DELETE_TASK_CONFIRMATION_MESSAGE(taskToDelete),
                    PROMPT_VALID_DELETE_CONFIRMATION_MESSAGE);

            if (canDelete) {
                TaskList.delete(index);
                return new CommandResult(deleteTaskMessage(taskToDelete));
            } else {
                return new CommandResult(ABORT_DELETE_MESSAGE);
            }
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(INVALID_LIST_NUMBER_MESSAGE, true);
        }
    }
}
