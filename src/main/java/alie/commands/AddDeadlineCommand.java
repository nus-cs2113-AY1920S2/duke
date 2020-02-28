package alie.commands;

import alie.Parser;
import alie.Storage;
import alie.TaskManager;
import alie.Ui;
import alie.exceptions.InvalidCmdException;
import alie.task.Deadline;

/**
 * Command to add deadline task with specific input format:
 * task_type task_name /by task_deadline
 */
public class AddDeadlineCommand extends Command {
    private final Deadline taskToAdd;

    public static final String COMMAND_KEYWORD = "deadline";
    protected static final String DEADLINE_DETAIL_DIVIDER = " /by ";

    /**
     * Construct a Deadline Object after parsing spiltCommands.
     * @param spiltCommands Array of String containing details in each index.
     * @throws InvalidCmdException If Name, Date or Deadline of Deadline Object is missing or empty.
     */
    public AddDeadlineCommand(String[] spiltCommands) throws InvalidCmdException {
        try {
            String[] details = Parser.parseDeadlineOrEventDetails(spiltCommands[1],
                    DEADLINE_DETAIL_DIVIDER, COMMAND_KEYWORD);
            String taskName = details[0];
            String taskDeadline = Parser.parseDate(details[1]);
            if (taskName.equals("") || taskDeadline.equals("")) {
                throw new InvalidCmdException(String.format(
                        InvalidCmdException.DEADLINE_OR_EVENT_MISSING_DETAILS_ERROR,
                        COMMAND_KEYWORD));
            }
            taskToAdd = new Deadline(taskName, taskDeadline);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCmdException(String.format(
                    InvalidCmdException.DEADLINE_OR_EVENT_MISSING_DETAILS_ERROR, COMMAND_KEYWORD));
        }
    }

    @Override
    public CommandResult execute(TaskManager taskLists, Ui ui, Storage storage) {
        taskLists.addNewTask(taskToAdd);
        return new CommandResult(String.format(ADD_TASK_ACK, taskToAdd.getTaskInfo(),
                taskLists.getNumOfTasks()));
    }
}
