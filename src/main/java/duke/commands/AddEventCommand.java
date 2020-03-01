package duke.commands;

import duke.data.TaskList;
import duke.format.DateTime;
import duke.task.Event;

import static duke.ui.Messages.addTaskMessage;

/**
 * <h3>Add Event Command</h3>
 * A <b>Command</b> to add a <b>Event</b> task into the <b>TaskList</b>.
 * @see Command
 * @see Event
 * @see TaskList
 */
public class AddEventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String FORMAT = "event <task description> /at <date> <time>";

    private final String task;
    private final DateTime dateTime;

    public AddEventCommand(String task, DateTime dateTime) {
        this.task = task;
        this.dateTime = dateTime;
    }

    /**
     * Executes the <b>Add Event Command</b> to add a <b>Event</b> task into the <b>TaskList</b>.
     *
     * @return The <b>Command Result</b> of the execution
     * @see Event
     * @see TaskList
     * @see CommandResult
     */
    @Override
    public CommandResult execute() {
        TaskList.add(new Event(task, dateTime));
        return new CommandResult(addTaskMessage());
    }
}
