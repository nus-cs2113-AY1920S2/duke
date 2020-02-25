package alie.commands;

import alie.Parser;
import alie.Storage;
import alie.TaskManager;
import alie.Ui;
import alie.exceptions.InvalidCmdException;
import alie.task.Event;

// Input format: <task type> <task name> /at <task details>
public class AddEventCommand extends Command {
    private final Event taskToAdd;

    public static final String COMMAND_KEYWORD = "event";
    protected static final String EVENT_DETAIL_DIVIDER = " /at ";
    public String ADD_EVENTS_ACK =
            INDENTATION + "Got it. I've added this task:" + System.lineSeparator() +
                    MORE_INDENTATION + "%1$s" + System.lineSeparator() +
                    INDENTATION + "Now you have %2$s tasks in the list.";

    public AddEventCommand(String[] cmd) throws InvalidCmdException {
        try {
            String[] details = Parser.parseDeadlineOrEventDetails(cmd[1], EVENT_DETAIL_DIVIDER);
            String taskName = details[0];
            String taskDetails = Parser.parseDate(details[1]);
            taskToAdd = new Event(taskName, taskDetails);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCmdException("Name or Date of Event cannot be empty!");
        }
    }

    @Override
    public CommandResult execute(TaskManager taskLists, Ui ui, Storage storage)
            throws InvalidCmdException {
        if (taskToAdd.getName().equals("") || taskToAdd.getDate().equals("")) {
            throw new InvalidCmdException("Name or Date of Event cannot be empty!");
        }
        taskLists.addNewTask(taskToAdd);
        return new CommandResult(String.format(ADD_EVENTS_ACK, taskToAdd.getTaskInfo(),
                taskLists.getNumOfTasks()));
    }
}
