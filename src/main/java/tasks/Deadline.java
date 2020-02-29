package tasks;

/**
 * Represents a Deadline object for tasks
 * A Deadline object corresponds to a description, time/date, and boolean isDone to check if Deadline task has been
 * completed
 */

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns String format of date/time of deadline
     * @return String by
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns String format for Deadline tasks to be stored in text file
     * @return String format of Deadline tasks
     */
    @Override
    public String storeText() {
        return "[D]/" + super.getStatus() + "/" + super.getDescription() + "/" + by;
    }

    /**
     * Returns String format for Deadline tasks to be printed on CLI
     * @return String format of Deadline tasks
     */
    @Override
    public String toString() {
        return "[D][ " + super.getStatusIcon() + " ] " + super.getDescription() + "(By: " + getBy() + ")";
    }
}