package duke.tasks;

/**
 * Class for Deadline task.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for Deadline class.
     * @param description String for description of deadline task.
     * @param by String for date of deadline task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.taskDescription = "deadline";
    }

    /**
     * To display deadline task message to user.
     * @return String consisting of deadline task, description and date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}