package commands;

import data.task.DeadlineTask;
import data.task.Task;

public class AddDeadlineCommand extends AddCommand {

    public static final String COMMAND_WORD = "deadline";
    public static final char COMMAND_TYPE = 'D';

    private final DeadlineTask deadlineTask;

    public AddDeadlineCommand(Task deadlineTask) {
        this.deadlineTask = (DeadlineTask) deadlineTask;
    }

    @Override
    public CommandResult execute() {
        duke.addTask(deadlineTask);
        return new CommandResult(String.format(
                MESSAGE_DEADLINE_SUCCESS,
                COMMAND_TYPE,
                deadlineTask.getChar(),
                deadlineTask.getTaskDescription(),
                deadlineTask.getTaskDeadline()));
    }
}
