package duke.commands;

import duke.data.TaskList;
import duke.format.DateTime;
import duke.task.Event;

import static duke.ui.Messages.addTaskMessage;

public class AddEventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String FORMAT = "event <task description> /at <date> <time>";

    private final String task;
    private final DateTime dateTime;

    public AddEventCommand(String task, DateTime dateTime) {
        this.task = task;
        this.dateTime = dateTime;
    }

    @Override
    public CommandResult execute() {
        TaskList.add(new Event(task, dateTime));
        return new CommandResult(addTaskMessage());
    }
}
