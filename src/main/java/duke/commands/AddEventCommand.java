package duke.commands;

import duke.data.TaskList;
import duke.task.Event;

import static duke.ui.Messages.addTaskMessage;

public class AddEventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String FORMAT = "event <task description> /at <datetime>";

    private final String task;
    private final String duration;

    public AddEventCommand(String task, String duration) {
        this.task = task;
        this.duration = duration;
    }

    @Override
    public CommandResult execute() {
        TaskList.add(new Event(task, duration));
        return new CommandResult(addTaskMessage());
    }
}
