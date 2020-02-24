package duke.tasks;

import duke.DukeException;

/**
 * Represents a deadline task with a task description and
 * a deadline field.
 */
public class Deadline extends Task {
    protected String by;

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
        if(description.isBlank() | by.isBlank())
        {
            throw new DukeException();
        }
        this.by = by.trim();
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String saveFormat() {
        return "d//" + super.saveFormat() + "//" + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
