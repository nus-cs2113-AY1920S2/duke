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
    public String ADD_DEADLINE_ACK =
            INDENTATION + "Got it. I've added this task:" + System.lineSeparator() +
                    MORE_INDENTATION + "%1$s" + System.lineSeparator() +
                    INDENTATION + "Now you have %2$s tasks in the list.";

    /**
     * Construct a Deadline Object after parsing spiltCommands.
     * @param spiltCommands Array of String containing details in each index.
     * @throws InvalidCmdException If there are any illegal inputs detected.
     */
    public AddDeadlineCommand(String[] spiltCommands) throws InvalidCmdException {
        try {
            String[] details = new Parser().parseDeadlineDetails(spiltCommands[1]);
            String taskName = details[0];
            String taskDetails = details[1];
            taskToAdd = new Deadline(taskName, taskDetails);
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
