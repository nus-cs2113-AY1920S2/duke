package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Task;

public class DeadlineCommand extends Command {
    public static final String DEADLINE_COMMAND_NAME= "deadline";
    public static final String COMMAND_DATE_TIME_DELIMITER = "\\s+/by\\s+";
    private final String description;
    private final String by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task addedTask = new Deadline(description, by);
        tasks.addTask(addedTask);
        ui.showAddedTaskMessage(addedTask, tasks.getNumTasks());
        attemptSave(tasks, ui, storage);
    }
}
