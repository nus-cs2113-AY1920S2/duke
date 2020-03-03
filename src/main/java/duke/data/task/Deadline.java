package duke.data.task;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {

    /**
     * Stores detail about the deadline
     */
    protected String by;

    /**
     * Constructor for Deadline Task Class.
     * <p> <br>
     * It creates a new Deadline task with the description provided by the user.
     * </p>
     *
     * @param description Contains the description of deadline.
     * @param by          Contains detail additional of deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string, description and deadline of the task in save-able format.<br>
     * Overrides the corresponding method in the parent class.
     *
     * @return output Represents the information related to the task as save-able format.
     */
    @Override
    public String toOutput() {
        String output = "D | " + (isDone ? '1' : '0') + " | " + description
                + " | " + by;
        return output;
    }

    /**
     * Returns the string, description and deadline of the task.<br>
     * Overrides the corresponding method in the parent class.
     *
     * @return info Represents the information related to the task.
     */
    @Override
    public String toString() {
        String info = "[D]" + super.toString() + " (by: " + by + ")";
        return info;
    }
}
