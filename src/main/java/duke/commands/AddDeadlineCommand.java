package duke.commands;

import duke.data.TaskList;
import duke.task.Deadline;

import static duke.ui.Messages.addTaskMessage;

public class AddDeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String FORMAT = "deadline <task description> /at <datetime>";

    private final String task;
    private final String deadline;

    public AddDeadlineCommand(String task, String deadline) {
        this.task = task;
        this.deadline = deadline;
    }

    @Override
    public CommandResult execute() {
        TaskList.add(new Deadline(task, deadline));
        return new CommandResult(addTaskMessage());
    }
}