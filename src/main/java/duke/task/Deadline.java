package duke.task;

/**
 * Represents a deadline.
 */
public class Deadline extends Task {

    /** Timing by which the task has to be finished */
    protected String by;

    public Deadline(String description,String by) {
        super(description);
        this.by = by;
    }

    /**
     * Obtains the timing by which the task has to be finished.
     *
     * @return Timing by which the task has to be finished.
     */
    public String getBy() {
        return by;
    }

    /**
     * Defines the string format of <code>Deadline</code> object.
     *
     * @return String format of the <code>Deadline</code> object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
