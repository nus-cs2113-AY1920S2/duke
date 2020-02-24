package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private String time;
    private boolean isStandardTime;
    protected LocalDate date;

    public Event (int taskID, String description, boolean isDone, String time) {
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
        return "[E]";
    }

    @Override
    public String toString () {
        String outputTime = isStandardTime? date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : time;
        return (super.taskID + 1) + ". [E] " + super.getStatusIcon() + " " + super.description
                + " (at: " + outputTime + ")";
    }
}
