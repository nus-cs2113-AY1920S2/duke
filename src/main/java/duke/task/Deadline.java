package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Structure for a deadline task.
 */

public class Deadline extends Task {
    private String time;
    private boolean isStandardTime;
    protected LocalDate date;

    public Deadline (int taskID, String description, boolean isDone, String time) {
        super (taskID, description, isDone);
        this.time = time;
        isStandardTime = false;
        try {
            this.date = LocalDate.parse(time);
            isStandardTime = true;
        } catch (DateTimeParseException e) {
        }
    }

    public String getTaskType () {
        return "[D]";
    }

    @Override
    public String toString () {
        String outputTime = isStandardTime? date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : time;
        return (super.taskID + 1) + ". [D] " + super.getStatusIcon() + " " + super.description
                + " (by: " + outputTime + ")";
    }
}
