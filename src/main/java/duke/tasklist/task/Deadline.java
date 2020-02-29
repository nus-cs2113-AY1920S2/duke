package duke.tasklist.task;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Stores a deadline task.
 */
public class Deadline extends Task {

    protected String by;
    protected LocalDate time;

    /**
     * Initializes the deadline class.
     * Transforms the user input time to LocalDate format if the input format is correct.
     *
     * @param description User task.
     * @param by Time limit.
     */
    public Deadline(String description,String by) {
        super(description);
        try {
            this.time = LocalDate.parse(by);
            this.by = time.format(DateTimeFormatter.ofPattern("MMM d yyyy", Locale.US)) + ", " + time.getDayOfWeek();
        } catch (DateTimeParseException e) {
            this.by = by;
        }
    }

    @Override
    public LocalDate getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toFile() {
        return "D | " + super.toFile() + " | " + by;
    }
}
