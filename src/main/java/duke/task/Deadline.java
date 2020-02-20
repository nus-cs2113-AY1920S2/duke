package duke.task;

import duke.task.Task;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        super.taskType = "D";
    }

    /**
     * Gets this deadline's time
     * @return the deadline's time
     */
    public String getBy() {
        return by;
    }

    /**
     * Sets this deadline's time
     */
    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + ")";
    }
}
