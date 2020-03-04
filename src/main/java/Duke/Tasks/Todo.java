package Duke.Tasks;

/**
 * A class of todo task type with action to complete.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo class.
     *
     * @param action the task description.
     */
    public Todo(String action) {
        super(action);
        this.taskType = "T";
    }

    /**
     * Creates a String with the Todo task.
     *
     * @return String indicating task type and task description.
     */
    @Override
    public String toString() {
        return "[" + taskType + "]" + super.toString();
    }
}
