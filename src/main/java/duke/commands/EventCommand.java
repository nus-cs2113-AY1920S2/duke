package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;
import duke.task.Task;

import java.time.LocalDateTime;

/**
 * Adds an Event to the TaskList.
 */
public class EventCommand extends Command {
    /** Word to be typed by the user to invoke this Command */
    public static final String EVENT_COMMAND_NAME = "event";
    /** Delimiter that separates the description and date of the Command */
    public static final String COMMAND_DATE_TIME_DELIMITER = "\\s+/at\\s+";
    /** Delimiter that separates the start time and end time of the Command */
    public static final String COMMAND_START_END_TIME_DELIMITER = "\\s*-\\s*";
    private final String description;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public EventCommand(String description, LocalDateTime startTime, LocalDateTime endTime) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task addedTask = new Event(description, startTime, endTime);
        tasks.addTask(addedTask);
        ui.showAddedTaskMessage(addedTask, tasks.getNumTasks());
        attemptSave(tasks, ui, storage);
    }
}
