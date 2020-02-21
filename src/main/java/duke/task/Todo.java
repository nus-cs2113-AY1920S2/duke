package duke.task;

/**
 * Represents a to-do item.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Defines the string format of to-do object.
     *
     * @return String format of to-do object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
