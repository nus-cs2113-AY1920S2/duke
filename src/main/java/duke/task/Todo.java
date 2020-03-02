package duke.task;

/**
 * The Todo class is a Task with specified description.
 * Todo class extends from Task class.
 * @author Lam Yue Wei
 * @version CS2113 AY19/20 Sem 2 Duke
 */
public class Todo extends Task {
    /**
     * Public constructor for Task.
     * @param description Description of the Task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Return a String representation of this Todo.
     * @return The Todo's icon, followed by the Task's toString.
     */
    public String toString() {
        return "[T]" + super.toString();
    }
}
