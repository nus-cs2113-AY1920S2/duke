package duke.task;

/**
 * Class representing a todo object.
 */
public class Todo extends Task {

    /**
     * Default constructor for todo class.
     * @param description Description of todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Formats todo task into a string.
     * @return String containing an indication of todo task and description of todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
