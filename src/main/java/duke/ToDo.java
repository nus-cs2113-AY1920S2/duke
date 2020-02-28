package duke;

/**
 * Represents a simple todo task.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo.
     * @return String representing the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
