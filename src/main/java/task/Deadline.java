package task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Entity class for the Deadline task.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        String dateTimeString = by.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm"));
        return "[D]" + super.toString() + " (by: " + dateTimeString + ")";
    }

    @Override
    public String convertToData() {
        int isDoneAsInt = isDone ? 1 : 0;
        return String.format("D|" + isDoneAsInt + "|" + this.description + "|" + this.by);
    }
}