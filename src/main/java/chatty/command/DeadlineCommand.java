package chatty.command;

import chatty.storage.Storage;
import chatty.task.Deadline;
import chatty.task.TaskList;
import chatty.ui.Ui;

import java.time.LocalDate;

/**
 * Deadline command used in the application.
 */
public class DeadlineCommand extends TaskCommand {

    private LocalDate dateTime;

    /**
     * Constructor for deadline command.
     *
     * @param description Description of the deadline task in the command.
     * @param dateTime    Datetime of the deadline task in the command.
     */
    public DeadlineCommand(String description, LocalDate dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Gets datetime in the deadline command.
     *
     * @return Datetime in the deadline command.
     */
    public LocalDate getDateTime() {
        return this.dateTime;
    }

    /**
     * Executes the deadline command.
     *
     * @param taskList Task list containing all tasks.
     * @param ui       UI to handle sending message to users.
     * @param storage  Storage to handle reading and writing of tasks from disk.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(this.getDescription(), this.getDateTime());
        taskList.addTask(deadline);
        ui.sendTaskAddedMessage(deadline, taskList.getTotalTaskNum());
    }
}
