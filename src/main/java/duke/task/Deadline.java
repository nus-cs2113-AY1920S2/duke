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

    @Override
    public String saveTask() {
        return "D | " + (super.isDone ? 1 : 0) + " | " + super.description + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
