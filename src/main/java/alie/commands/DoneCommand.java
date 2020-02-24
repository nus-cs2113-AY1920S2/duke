package alie.commands;

import alie.Storage;
import alie.TaskManager;
import alie.Ui;
import alie.exceptions.InvalidCmdException;
import alie.task.Task;

public class DoneCommand extends Command {
    public static final String COMMAND_KEYWORD = "done";
    public static final String DONE_CMD_ACK =
            INDENTATION + "Nice! I've marked this task as done:" + System.lineSeparator() +
            INDENTATION +  "%1$s. %2$s";

    public final int taskIndex;

    public DoneCommand(String index) throws InvalidCmdException {
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
            taskLists.markTaskCompleted(taskIndex);
            Task completedTask = taskLists.getTaskFromIndex(taskIndex);
            return new CommandResult(String.format(DONE_CMD_ACK, convertToOneBased(taskIndex),
                    completedTask.getTaskInfo()));
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new InvalidCmdException("INDEX provided is not a valid index.");
        }
    }
}
