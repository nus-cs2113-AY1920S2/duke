package duke.task;

/**
 * Represents a deadline in the task list, a subclass of Task.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructor for event class.
     *
     * @param description description of the deadline to be created
     * @param by the deadline of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string, description and deadline of the task in save-able format.<br>
     * Overrides the corresponding method in the parent class.
     *
     * @return save Represents the information related to the task as save-able format.
     */
    @Override
    public String saveTask() {
        String save = "D | " + (super.isDone ? 1 : 0) + " | " + super.description + " | " + by;
        return save;
    }

    /**
     * Returns the string, description and deadline of the task.<br>
     * Overrides the corresponding method in the parent class.
     *
     * @return s Represents the information related to the task.
     */
    @Override
    public String toString() {
        String s = "[D]" + super.toString() + " (by: " + by + ")";
        return s;
    }
}
