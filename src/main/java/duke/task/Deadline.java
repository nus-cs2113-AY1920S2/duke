package duke.task;

/**
 * Class representing Deadline task.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Default constructor for Deadline class.
     * @param description String containing the description of deadline task
     * @param by String containing date and time when deadline task is due
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }


    /**
     * Formats deadline task into a string.
     * @return String indicating deadline task, description of deadline task and date/time of deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }


}
