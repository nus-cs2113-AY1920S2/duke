package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Task that is connected to a specific date.
 */
public class Event extends Task {

    /** Time at which event occurs. */
    private LocalDate eventTime;

    /**
     * Get the event time.
     *
     * @return The event time.
     */
    public LocalDate getEventTime() {
        return eventTime;
    }

    /**
     * Returns the type of the task.
     *
     * @return The type of the task.
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Set the event time.
     *
     * @param eventTime The event time to set.
     */
    public void setEventTime(LocalDate eventTime) {
        this.eventTime = eventTime;
    }

    /**
     * Constructs an event task.
     *
     * @param description Task description.
     * @param eventTime Date of event.
     */
    public Event(String description, LocalDate eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    /**
     * Constructs an event task.
     *
     * @param isDone Set whether the task is already done or not.
     * @param description Task description.
     * @param eventTime Date of event.
     */
    public Event(boolean isDone, String description, LocalDate eventTime) {
        super(isDone, description);
        this.eventTime = eventTime;
    }

    /**
     * Converts the Task object to data representation to be stored in a data file.
     * File format:
     * taskId, taskType, taskIsDone, taskDesc, taskDate
     *
     * @param taskId ID of task.
     * @return String representing the Task object in comma-separated data format.
     */
    @Override
    public String toData(int taskId) {
        String dataLine = (taskId + "," + this.getType() + "," + this.isDone() + "," + this.getDescription() + ","
                + this.getEventTime());
        return dataLine;
    }

    /**
     * Represents the input task as a string.
     *
     * @return String representing the Task object.
     */
    @Override
    public String toString() {
        return ("[E]" + super.toString() + " (at: "
                + this.getEventTime().format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
    }
}
