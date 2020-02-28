package duke.tasks;

import duke.DukeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a task description and
 * a deadline field.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructor to create a deadline task.
     *
     * @param description the name of the task
     * @param by the date which the task should be completed by
     * @throws DukeException throws an exception if the description
     * or the by fields are empty
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
     * Method to get the deadline of the task.
     *
     * @return date of deadline
     */
    public LocalDate getDate() {
        return this.by;
    }

    /**
     * Format which the tasks are saved into the .txt file.
     *
     * @return String with the save format
     */
    @Override
    public String saveFormat() {
        return "d//" + super.saveFormat() + "//" + by;
    }

    /**
     * Format which the tasks are printed.
     *
     * @return String with the print format
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
