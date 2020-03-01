package duke.commands;

import duke.data.TaskList;
import duke.format.DateTime;
import duke.task.Deadline;

import static duke.ui.Messages.addTaskMessage;

public class AddDeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String FORMAT = "deadline <task description> /at <date> <time>";

    private final String task;
    private final DateTime deadline;

    public AddDeadlineCommand(String task, DateTime deadline) {
        this.task = task;
        this.deadline = deadline;
    }

    @Override
    public CommandResult execute() {
        TaskList.add(new Deadline(task, deadline));
        return new CommandResult(addTaskMessage());
    }
}