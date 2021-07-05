/**
 * Represents the deadline objects that are tracked
 * by Duke. A deadline object corresponds to tasks with a due date.
 */
public class Deadline extends Task {
    protected String dueDate;

    /**
     * Constructor for the deadline object.
     * @param description information about the deadline.
     * @param dueDate the date and time where the deadline is due.
     */
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Method used to format the deadline description before printing.
     * @return a string that describes the deadline object.
     */
    @Override
    public String showFullDescription() {
        return description + " (by: " + dueDate + ")";
    }

    /**
     * Getter for due date of deadline object.
     * @return a string that represents the due date.
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     * Getter for the icon of the deadline object.
     * @return a string that acts as a icon to differentiate
     * between the various types of tasks.
     */
    @Override
    public String getTypeIcon() {
        return "[D]";
    }
}
