package duke.task;

/**
 * Represents a to-do in the task list, a subclass of Task.
 */
public class ToDo extends Task {

    /**
     * Constructor for to-do class.
     *
     * @param description description of the task to be created
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String saveTask() {
        return "T | " + (super.isDone ? 1 : 0) + " | " + super.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}