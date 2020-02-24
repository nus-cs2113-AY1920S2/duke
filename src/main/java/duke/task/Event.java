package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event in the task list
 */
public class Event extends Task {

    //protected String at;
    protected LocalDate at;

    public Event(String description, String at) {
        super(description.trim());
        at = at.trim();
        this.at = LocalDate.parse(at);
    }

    public LocalDate getDate() {
        return this.at;
    }

    @Override
    public String formatResult() {
        return "e|" + super.formatResult() + "|" + at;
    }
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
