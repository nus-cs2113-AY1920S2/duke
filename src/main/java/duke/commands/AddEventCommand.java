package duke.commands;

import duke.data.TaskList;

import static duke.format.Printer.addTaskMessage;

public class AddEventCommand extends Command {
    public static final String COMMAND_WORD = "event";

    private final String task;
    private final String duration;

    public AddEventCommand(String task, String duration) {
        this.task = task;
        this.duration = duration;
    }

    @Override
    public CommandResult execute() {
        TaskList.addEvent(task, duration);
        return new CommandResult(addTaskMessage());
    }
}
