package Tasks;

/**
 * A subclass of <code>Task</code> class.
 *
 * @see Task
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Class constructor of <code>Deadline</code> class.
     * @param description Description of the <code>Deadline</code> task.
     * @param by Due date/time of the <code>Deadline</code> task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a <code>String</code> of the <code>Deadline</code> task to be printed.
     * @return <code>String</code> of symbol, done status, description and due date/time of <code>Deadline</code> task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns a <code>String</code> of the <code>Deadline</code> task to be written to the text file.
     * @return <code>String</code> of symbol, done status, description and due date/time of <code>Deadline</code> task.
     */
    @Override
    public String toFileString() {
        return "D," + super.toFileString() + "," + by;
    }
}