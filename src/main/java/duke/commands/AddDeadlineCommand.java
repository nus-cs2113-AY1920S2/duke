package duke.commands;

import duke.data.TaskList;

import static duke.format.Printer.addTaskMessage;

public class AddDeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

    private final String task;
    private final String deadline;

    public AddDeadlineCommand(String task, String deadline) {
        this.task = task;
        this.deadline = deadline;
    }

    @Override
    public CommandResult execute() {
        TaskList.addDeadline(task, deadline);
        return new CommandResult(addTaskMessage());
    }
}