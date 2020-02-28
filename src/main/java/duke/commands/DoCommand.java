package duke.commands;

import duke.data.TaskList;

import static duke.exception.ExceptionMessages.INVALID_LIST_NUMBER_MESSAGE;
import static duke.ui.Messages.alreadyDoneTaskMessage;
import static duke.ui.Messages.doTaskMessage;

/**
 * <h3>Do Command</h3>
 * A <b>Command</b> to mark a specified <i>task</i> in the <b>TaskList</b> as done.
 * @see Command
 * @see TaskList
 */
public class DoCommand extends Command {
    public static final String COMMAND_WORD = "done";
    public static final String FORMAT = "done <list number>";

    private final int index;

    public DoCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the <b>Do Command</b> to mark a specified <i>task</i> in the <b>TaskList</b> as done.
     *
     * @return The <b>Command Result</b> of the execution
     * @see TaskList
     * @see CommandResult
     */
    @Override
    public CommandResult execute() {
        try {
            // Checks if task has not been previously done before
            boolean isBeingDone = TaskList.doTask(index);

            if (isBeingDone) {
                return new CommandResult(doTaskMessage(index));
            } else {
                return new CommandResult(alreadyDoneTaskMessage(index));
            }
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(INVALID_LIST_NUMBER_MESSAGE, true);
        }
    }
}
