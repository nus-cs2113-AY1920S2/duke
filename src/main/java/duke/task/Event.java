package duke.task;

import duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event in the task list
 */
public class Event extends Task {

    protected LocalDate at;

    /**
     * Constructor to create a new event task.
     *
     * @param description the description of the task
     * @param at the due date of the task
     * @throws DukeException throws an exception if the description or date of the task is empty
     */
    public Event(String description, String at) throws DukeException {
        super(description.trim());
        if (description.isBlank() | at.isBlank())
        {
            throw new DukeException();
        }
        at = at.trim();
        this.at = LocalDate.parse(at);
    }

    /**
     * Method to get the deadline of the task
     * @return the due date of the task
     */
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
