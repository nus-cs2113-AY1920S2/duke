package duke.taskManager;

/**
 * The Deadline class is a Task with specified String description and String dateTime.
 * Deadline class extends from Task class.
 */
public class Deadline extends Task {
    private String by;

    /**
     * Public constructor for Deadline Task
     *
     * @param description Description of task
     * @param by          Time of Task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Getter for the Time of the task
     *
     * @return Time of the task
     */
    public String getDeadline() {
        return by;
    }

    /**
     * Return a string representation of the task
     *
     * @return [taskStatus] followed by the description
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + getDeadline() + ")";
    }

}
