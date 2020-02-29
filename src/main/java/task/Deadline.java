package task;

public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for the Deadline class
     *
     * @param description the description of the deadline
     * @param by the deadline of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Get the deadline of the task
     *
     * @return the task's deadline
     */
    public String getBy() {
        return by;
    }

    /**
     * Set the deadline of the task
     *
     * @param by the task's deadline
     */
    public void setBy(String by) {
        this.by = by;
    }

    /**
     * Returns the status and the description as a string prepended with "[D]"
     * and appended with "(by ...")
     *
     * @return String of status and description of task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.by + ")";
    }

    /**
     * The description of the task to be saved
     *
     * @return Description of task and its status with T to indicate Deadline
     */
    @Override
    public String saveTask() {
        return "D|" + super.isDoneNum() + "|" + super.saveTask() + "|" + this.by;
    }
}
