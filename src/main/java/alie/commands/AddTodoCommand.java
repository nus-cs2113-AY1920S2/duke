package alie.commands;

import alie.Storage;
import alie.TaskManager;
import alie.Ui;
import alie.exceptions.InvalidCmdException;
import alie.task.ToDo;

/**
 * Command to add deadline task with specific input format:
 * task_type task_name
 */
public class AddTodoCommand extends Command {
    private final ToDo taskToAdd;

    public static final String COMMAND_KEYWORD = "todo";

    /**
     * Construct a ToDo Object after parsing spiltCommands.
     * @param splitCommands Array of String containing details in each index.
     * @throws InvalidCmdException If Name of ToDo Object is missing.
     */
    public AddTodoCommand(String[] splitCommands) throws InvalidCmdException {
        try {
            if (splitCommands[1].equals("")) {
                throw new InvalidCmdException(String.format(
                        InvalidCmdException.TODO_MISSING_NAME_ERROR, COMMAND_KEYWORD));
            }
            taskToAdd = new ToDo(splitCommands[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCmdException(String.format(
                    InvalidCmdException.TODO_MISSING_NAME_ERROR, COMMAND_KEYWORD));
        }
    }

    @Override
    public CommandResult execute(TaskManager taskLists, Ui ui, Storage storage) {
        taskLists.addNewTask(taskToAdd);
        return new CommandResult(String.format(ADD_TASK_ACK, taskToAdd.getTaskInfo(),
                taskLists.getNumOfTasks()));
    }
}
