package task;

/**
 * Represents a task of the type Todo.
 * @see Task
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
        this.taskType = "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
