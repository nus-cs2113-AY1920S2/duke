/**
 * Represents a deadline task
 * A Deadline object contains a description, time/date and boolean to track if deadline has been completed
 */

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description,boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns the value of the date/time of deadline
     * @return String by
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns the Deadline' details , separated by commas
     * @return String format to be stored in text file
     */
    @Override
    public String storeText() {
        return "[D]," + super.getStatus() + "," + super.getDescription() + "," + by;
    }

    /**
     * Returns string format for displaying on CLI
     * @return  String representation for Deadline
     */
    @Override
    public String toString() {
        return "[D][ " + super.getStatusIcon() + " ] " + super.getDescription() + "(By: " + getBy() + ")";
    }
}