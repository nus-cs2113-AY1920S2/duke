package Features;

/**
 * Extension of <code>Task</code> class specifying an <code>Todo</code> task.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String getType() {
        return "Todo";
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
