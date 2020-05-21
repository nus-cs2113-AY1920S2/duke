package commands;

import exception.DukeException;
import storage.Storage;
import task.Event;
import task.TaskList;
import ui.Ui;

import java.time.LocalDateTime;

/**
 * Command class for the AddEvent command.
 */
public class AddEventCommand extends AddCommand {

    private String taskDescription;
    private LocalDateTime dateAndTime;

    public static final String COMMAND_WORD = "event";

    public AddEventCommand(String taskDescription, LocalDateTime dateAndTime) {
        this.taskDescription = taskDescription;
        this.dateAndTime = dateAndTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Event eventTask = new Event(this.taskDescription, this.dateAndTime);
        tasks.addTask(eventTask);
        storage.saveTasks(tasks);
        ui.printNewTask(tasks);
    }
}
