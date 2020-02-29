package chatty.command;

import chatty.storage.Storage;
import chatty.task.TaskList;
import chatty.ui.Ui;

import java.time.LocalDate;

/**
 * Date command used in the application.
 */
public class DateCommand extends Command {

    private LocalDate dateTime;

    /**
     * Constructor for date command.
     *
     * @param dateTime The datetime in the date command.
     */
    public DateCommand(LocalDate dateTime) {
        super();
        this.dateTime = dateTime;
    }

    /**
     * Gets datetime in the date command.
     *
     * @return Datetime in the date command.
     */
    public LocalDate getDateTime() {
        return this.dateTime;
    }

    /**
     * Executes the date command.
     *
     * @param taskList Task list containing all tasks.
     * @param ui       UI to handle sending message to users.
     * @param storage  Storage to handle reading and writing of tasks from disk.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList tasksOnDate = taskList.getTasksOnDate(this.getDateTime());
        ui.listTasksOnDate(tasksOnDate, this.getDateTime());
    }
}
