package alie.commands;

import alie.Storage;
import alie.TaskManager;
import alie.Ui;
import alie.exceptions.InvalidCmdException;
import alie.task.Task;

/**
 * Command to mark a task as done.
 */
public class DoneCommand extends Command {
    public static final String COMMAND_KEYWORD = "done";


    public final int taskIndex;

    /**
     * Default constructor to initialise taskIndex to reflect correctly the index of the task
     * to be marked as done.
     * @param spiltCommands Array of String with first index containing index of interested task.
     * @throws InvalidCmdException If index provided by user is not a number.
     */
    public DoneCommand(String[] spiltCommands) throws InvalidCmdException {
        try {
            taskIndex = convertToZeroBased(Integer.parseInt(spiltCommands[1]));
        } catch (NumberFormatException e) {
            throw new InvalidCmdException(InvalidCmdException.INVALID_NUM_ERROR);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCmdException(InvalidCmdException.MISSING_INDEX_ERROR);
        }
    }

    @Override
    public CommandResult execute(TaskManager taskLists, Ui ui, Storage storage)
            throws InvalidCmdException {
        try {
            Task targetTask = taskLists.getTaskFromIndex(taskIndex);
            if (targetTask.getisDone()) {
                throw new InvalidCmdException(InvalidCmdException.COMPLETED_TASK_ERROR);
            }
            taskLists.markTaskCompleted(taskIndex);
            return new CommandResult(String.format(DONE_ACK, convertToOneBased(taskIndex),
                    targetTask.getTaskInfo()));
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new InvalidCmdException(String.format(InvalidCmdException.INVALID_ID_ERROR,
                    getRangeOfValidIndex(taskLists)));
        }
    }
}
