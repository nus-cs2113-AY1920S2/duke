package alie.commands;

import alie.Parser;
import alie.Storage;
import alie.TaskManager;
import alie.Ui;
import alie.exceptions.InvalidCmdException;
import alie.task.Event;

/**
 * Command to add deadline task with specific input format:
 * task_type task_name /at task_startDate
 */
public class AddEventCommand extends Command {
    private final Event taskToAdd;

    public static final String COMMAND_KEYWORD = "event";
    protected static final String EVENT_DETAIL_DIVIDER = " /at ";

    /**
     * Construct an Event Object after parsing spiltCommands.
     * @param splitCommands Array of String containing details in each index.
     * @throws InvalidCmdException If Name or Start Date of Event Object is missing or empty.
     */
    public AddEventCommand(String[] splitCommands) throws InvalidCmdException {
        try {
            String[] details = Parser.parseDeadlineOrEventDetails(splitCommands[1],
                    EVENT_DETAIL_DIVIDER, COMMAND_KEYWORD);
            String taskName = details[0];
            String taskStartDate = Parser.parseDate(details[1]);
            if (taskName.equals("") || taskStartDate.equals("")) {
                throw new InvalidCmdException(String.format(
                        InvalidCmdException.DEADLINE_OR_EVENT_MISSING_DETAILS_ERROR,
                        COMMAND_KEYWORD));
            }
            taskToAdd = new Event(taskName, taskStartDate);
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
