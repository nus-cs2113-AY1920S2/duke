package chatty.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for deadline task.
 */
public class Deadline extends Task {

    private LocalDate dateTime;

    /**
     * Constructor for deadline task.
     * @param description Description of the deadline task.
     * @param dateTime Datetime of the deadline.
     */
    public Deadline(String description, LocalDate dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Converts the deadline task to string.
     * @return String representing the deadline task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }

    /**
     * Converts the deadline task to a string to be stored in file.
     * @return String representing the deadline task which should be stored in file.
     */
    @Override
    public String getFileString() {
        return String.format("D|%s|%s|%s", this.getDoneStatus(), this.getDescription(), this.dateTime);
    }

    /**
     * Returns the date time of the deadline.
     * @return Date time of the deadline.
     */
    public LocalDate getDateTime() {
        return dateTime;
    }
}
