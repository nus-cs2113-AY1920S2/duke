package alie.commands;

import alie.Storage;
import alie.TaskManager;
import alie.Ui;
import alie.exceptions.InvalidCmdException;
import alie.task.Task;

/**
 * Command to delete task from task list.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_KEYWORD = "delete";

    public int taskIndex;

    /**
     * Default constructor to initialise taskIndex to reflect correctly the index of the task
     * to be deleted.
     * @param spiltCommands Array of String with first index containing index of interested task.
     * @throws InvalidCmdException If index provided by user is not a number.
     */
    public DeleteCommand(String[] spiltCommands) throws InvalidCmdException {
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
            Task deletedTask = taskLists.getTaskFromIndex(taskIndex);
            taskLists.deleteTask(taskIndex);
            return new CommandResult(String.format(DELETE_TASK_ACK, deletedTask.getTaskInfo(),
                    taskLists.getNumOfTasks()));
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new InvalidCmdException(String.format(InvalidCmdException.INVALID_ID_ERROR,
                    getRangeOfValidIndex(taskLists)));
        }
    }
}
