package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;
import duke.task.Task;

public class EventCommand extends Command {
    public static final String EVENT_COMMAND_NAME = "event";
    public static final String COMMAND_DATE_TIME_DELIMITER = "\\s/at\\s";
    private final String description;
    private final String at;

    public EventCommand(String description, String at) {
        this.description = description;
        this.at = at;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task addedTask = new Event(description, at);
        tasks.addTask(addedTask);
        ui.showAddedTaskMessage(addedTask, tasks.getNumTasks());
        attemptSave(tasks, ui, storage);
    }
}
