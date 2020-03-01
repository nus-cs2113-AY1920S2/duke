package task;

/**
 * A sub-class of the Task class for deadlines.
 * Contains due date of the deadline.
 *
 * @see Task
 */

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    /**
     * Returns a string representation of deadline task to be output to
     * the bot's UI.
     *
     * @return string of symbol, done status, description and
     *         due date of deadline task.
     * */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getBy() + ")";
    }

    /**
     * Returns a string representation of event task to be written to
     * the storage file.
     *
     * @return string of symbol, done status, description and
     *         due date of deadline task.
     * */
    @Override
    public String toFileString() {
        return "D," + super.toFileString() + "," + this.getBy();
    }
}