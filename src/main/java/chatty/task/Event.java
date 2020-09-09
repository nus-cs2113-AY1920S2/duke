package chatty.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for event task.
 */
public class Event extends Task {

    private LocalDate startTime;
    private LocalDate endTime;

    /**
     * Constructor for event task.
     *
     * @param description Description of the event task.
     * @param startTime   Event start time of the event task.
     * @param endTime     Event end time of the event task.
     */
    public Event(String description, LocalDate startTime, LocalDate endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Converts the event task to string.
     *
     * @return String representing the event task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s to %s)", super.toString(),
                this.startTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                this.endTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }

    /**
     * Converts the event task to a string to be stored in file.
     *
     * @return String representing the event task which should be stored in file.
     */
    @Override
    public String getFileString() {
        return String.format("E|%s|%s|%s to %s", this.getDoneStatus(), this.getDescription(), this.startTime,
                this.endTime);
    }

    /**
     * Gets event start time in the event task.
     *
     * @return Event start time in the event task.
     */
    public LocalDate getStartTime() {
        return startTime;
    }

    /**
     * Gets event end time in the event task.
     *
     * @return Event end time in the event task.
     */
    public LocalDate getEndTime() {
        return endTime;
    }
}
