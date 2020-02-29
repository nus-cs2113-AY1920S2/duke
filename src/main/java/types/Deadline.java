package types;

/**
 * Represents a task with a deadline
 */
public class Deadline extends Task {

    /**
     * When the deadline is by
     */
    protected String by;

    /**
     * A task with a specific deadline
     * @param description what the task is
     * @param by when the task needs to be done by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * The task in string form
     * @return string of the task with the deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }

    /**
     * Get type of task
     * @return type
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * Returns when deadline is by
     * @return when deadline is by
     */
    @Override
    public String getBy() {
        return by;
    }
}
