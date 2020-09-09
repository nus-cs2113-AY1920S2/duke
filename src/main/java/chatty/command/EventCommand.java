package chatty.command;

import chatty.storage.Storage;
import chatty.task.Event;
import chatty.task.TaskList;
import chatty.ui.Ui;

import java.time.LocalDate;

/**
 * Event command used in the application.
 */
public class EventCommand extends TaskCommand {

    private LocalDate startTime;
    private LocalDate endTime;

    /**
     * Constructor for event command.
     *
     * @param description Description of the event task in the command.
     * @param startTime   Event start time in the event command.
     * @param endTime     Event end time in the event command.
     */
    public EventCommand(String description, LocalDate startTime, LocalDate endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Gets event start time in the event command.
     *
     * @return Event start time in the event command.
     */
    public LocalDate getStartTime() {
        return startTime;
    }

    /**
     * Gets event end time in the event command.
     *
     * @return Event end time in the event command.
     */
    public LocalDate getEndTime() {
        return endTime;
    }

    /**
     * Executes the event command.
     *
     * @param taskList Task list containing all tasks.
     * @param ui       UI to handle sending message to users.
     * @param storage  Storage to handle reading and writing of tasks from disk.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Event event = new Event(this.getDescription(), this.getStartTime(), this.getEndTime());
        taskList.addTask(event);
        ui.sendTaskAddedMessage(event, taskList.getTotalTaskNum());
    }
}
