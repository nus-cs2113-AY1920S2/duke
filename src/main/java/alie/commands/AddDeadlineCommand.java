package alie.commands;

import alie.Parser;
import alie.Storage;
import alie.TaskManager;
import alie.Ui;
import alie.exceptions.InvalidCmdException;
import alie.task.Deadline;

// Input format: <task type> <task name> /by <task details>
public class AddDeadlineCommand extends Command {
    private final Deadline taskToAdd;

    public static final String COMMAND_KEYWORD = "deadline";
    protected static final String DEADLINE_DETAIL_DIVIDER = " /by ";
    public String ADD_DEADLINE_ACK =
            INDENTATION + "Got it. I've added this task:" + System.lineSeparator() +
                    MORE_INDENTATION + "%1$s" + System.lineSeparator() +
                    INDENTATION + "Now you have %2$s tasks in the list.";

    public AddDeadlineCommand(String[] spiltCommands) throws InvalidCmdException {
        try {
            String[] details = Parser.parseDeadlineOrEventDetails(spiltCommands[1],
                    DEADLINE_DETAIL_DIVIDER);
            String taskName = details[0];
            String taskDeadline = Parser.parseDate(details[1]);
            taskToAdd = new Deadline(taskName, taskDeadline);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCmdException("Name or Date of Deadline cannot be empty!");
        }
    }

    @Override
    public CommandResult execute(TaskManager taskLists, Ui ui, Storage storage)
            throws InvalidCmdException {
        if (taskToAdd.getName().equals("") || taskToAdd.getDate().equals("")) {
            throw new InvalidCmdException("Name or Date of Deadline cannot be empty!");
        }
        taskLists.addNewTask(taskToAdd);
        return new CommandResult(String.format(ADD_DEADLINE_ACK, taskToAdd.getTaskInfo(),
                taskLists.getNumOfTasks()));
    }
}
