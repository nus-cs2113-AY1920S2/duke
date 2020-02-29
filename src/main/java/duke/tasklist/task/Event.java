package duke.tasklist.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import static duke.common.Constants.EVENT;

/**
 * Stores an event class.
 */
public class Event extends Task {

    protected String at;
    protected LocalDate time;

    /**
     * Initializes the deadline class.
     * Transforms the user input time to LocalDate format if the input format is correct.
     *
     * @param description User task.
     * @param at Time slot to do the event.
     */
    public Event(String description,String at) {
        super(description);
        try {
            this.time = LocalDate.parse(at);
            this.at = time.format(DateTimeFormatter.ofPattern("MMM d yyyy", Locale.US)) + ", " + time.getDayOfWeek();
        } catch (DateTimeParseException e) {
            this.at = at;
        }
    }

    @Override
    public LocalDate getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String toFile() {
        return "E | " + super.toFile() + " | " + at;
    }
}
