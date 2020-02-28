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
    public static final String DELETE_CMD_ACK =
            INDENTATION + "Noted. I've removed this task:" + System.lineSeparator() +
            MORE_INDENTATION +  "%1$s" + System.lineSeparator() +
            INDENTATION + "Now you have %2$s tasks in the list.";

    public int taskIndex = -1;

    /**
     * Default constructor to initialise taskIndex to reflect correctly the index of the task
     * to be deleted.
     * @param index Index provided by User that is one-based numbering.
     * @throws InvalidCmdException If index provided by user is not a number.
     */
    public DeleteCommand(String index) throws InvalidCmdException {
        try {
            taskIndex = convertToZeroBased(Integer.parseInt(index));
        } catch (NumberFormatException e) {
            throw new InvalidCmdException("INDEX provided is not a number.");
        }
    }

    @Override
    public CommandResult execute(TaskManager taskLists, Ui ui, Storage storage)
            throws InvalidCmdException {
        try {
            Task deletedTask = taskLists.getTaskFromIndex(taskIndex);
            taskLists.deleteTask(taskIndex);
            return new CommandResult(String.format(DELETE_CMD_ACK, deletedTask.getTaskInfo(),
                    taskLists.getNumOfTasks()));
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new InvalidCmdException("INDEX provided is not a valid index.");
        }
    }
}
