package types;

/**
 * Represents a task without a deadline
 */
public class Todo extends Task {

    /**
     * A task that needs to be done
     * @param description the task that needs to be done
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * The task in string form
     * @return string of task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Get type of task
     * @return type
     */
    @Override
    public String getType() {
        return "T";
    }

}
