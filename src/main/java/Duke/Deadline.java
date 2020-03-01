package Duke;

/**
 * Represents a deadline object which is a task with a deadline date associated with it.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for a new deadline.
     * @param description description for the deadline
     * @param by the date for the deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor for deadlines that are read from a storage file.
     * @param description description of the deadline
     * @param by date of the deadline
     * @param status status of the deadline
     */
    public Deadline(String description, String by, String status) {
        super(description);
        this.by = by;
        if (status.equals("1")) {
            this.markAsDone();
        }
    }

    /**
     * @return a string containing the status, description and date of the deadline for printing
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    /**
     * @return a string of information of the deadline in the format for saving to a file
     */
    @Override
    public String toSaveFormat() {
        return(super.toSaveFormat() + "D|" + this.by);
    }
}