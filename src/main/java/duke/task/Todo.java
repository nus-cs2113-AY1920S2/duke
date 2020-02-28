package duke.task;

/**
 * a type of task that simply needs to be done at
 * own time and own target, anywhere and anytime
 */
public class Todo extends Task {
    /**
     * Returns the task created with the specified
     * description.
     *
     * @param description the description of the task
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getExtra() {
        return null;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
