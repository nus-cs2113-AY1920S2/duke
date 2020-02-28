package duke.task;

/**
 * Represents a task of the type Todo.
 * @see Task
 */
public class Todo extends Task {

    /**
     * Constructor for Todo Task class.
     * <p> <br>
     * Creates a new Todo with the task description.
     * Also sets the taskType to "T", representing Todo.
     *</p>
     * @param description Description of the task provided by the user.
     */
    public Todo(String description) {
        super(description);
        this.taskType = "T";
    }

    /**
     * Returns task description for displaying of task details.
     *
     * @return String of re-formatted task details.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
