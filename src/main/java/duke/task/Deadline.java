package duke.task;

import duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline in the task list
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor to create a new deadline task.
     *
     * @param description the description of the task
     * @param by the due date of the task
     * @throws DukeException throws an exception if the description or date of the task is empty
     */
    public Deadline(String description, String by) throws DukeException {
        super(description.trim());
        if (description.isBlank() | by.isBlank())
        {
            throw new DukeException();
        }
        by = by.trim();
        this.by = LocalDate.parse(by);
    }

    /**
     * Method to get the deadline of the task
     * @return the due date of the task
     */
    public LocalDate getDate() {
        return this.by;
    }

    @Override
    public String formatResult() {
        return "d|" + super.formatResult() + "|" + by;
    }

    public String toString() {
        return "[D]" + super.toString() + "(by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

