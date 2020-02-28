package duke.tasks;

import duke.DukeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with a task description and the
 * date of the event.
 */
public class Event extends Task {

    protected LocalDate timePeriod;

    public Event(String description, String t) throws DukeException {
        super(description.trim());
        if (description.isBlank() | t.isBlank())
        {
            throw new DukeException();
        }
        t = t.trim();
        this.timePeriod = LocalDate.parse(t);
    }

    /**
     * Method to get the date of event of the task.
     *
     * @return date of event
     */
    public LocalDate getDate() {
        return this.timePeriod;
    }

    /**
     * Format which the tasks are saved into the .txt file.
     *
     * @return String with the save format
     */
    @Override
    public String saveFormat() {
        return "e//" + super.saveFormat() + "//" + timePeriod;
    }

    /**
     * Format which the tasks are printed.
     *
     * @return String with the print format
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + timePeriod.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
