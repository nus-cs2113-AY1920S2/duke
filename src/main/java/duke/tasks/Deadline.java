package duke.tasks;

/**
 * Class to represent a deadline task
 */
public class Deadline extends Task {
    protected String dueDateTime;

    public Deadline(String description, String dueDateTime) {
        super(description);
        this.dueDateTime = dueDateTime;
    }

    public Deadline(String description, String dueDateTime, boolean isDone) {
        this(description, dueDateTime);
        this.isDone = isDone;
    }

    /**
     * get the string representation of this deadline
     * @return string representation of this deadline
     */
    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: " + dueDateTime + ")";
    }

    /**
     * get the string representation of this deadline formatted for saving to file
     * @return the string representation of this deadline formatted for saving to file
     */
    public String toFormattedString() {
        String done = isDone ? "y" : "n";
        return "D," + done + "," + description + "," + dueDateTime;
    }
}
