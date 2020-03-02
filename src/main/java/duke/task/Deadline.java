package duke.task;

/**
 * The Deadline class is a Task with specified description and due date.
 * Deadline class extends from Task class.
 * @author Lam Yue Wei
 * @version CS2113 AY19/20 Sem 2 Duke
 */
public class Deadline extends Task {
    String date;

    /**
     * Public constructor for Deadline.
     * @param description Description of the Deadline Task.
     * @param date Due date of the Deadline Task.
     */
    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    /**
     * Getter method for the due date.
     * @return Due date.
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Return a String representation of this Deadline.
     * @return The Deadline's icon, followed by the Task's toString, followed by the due date.
     */
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date + ")";
    }
}
