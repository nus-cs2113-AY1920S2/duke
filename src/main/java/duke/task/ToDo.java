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

    /**
     * Returns both the status and description of the task in save-able format.<br>
     * Overrides the corresponding method in the parent class.
     *
     * @return output Represents the description of the task in save-able format.
     */
    @Override
    public String saveTask() {
        String save ="T | " + (super.isDone ? 1 : 0) + " | " + super.description;
        return save;
    }

    /**
     * Returns both the status and description of the task.<br>
     * Overrides the corresponding method in the parent class.
     *
     * @return s Represents the description of the task.
     */
    @Override
    public String toString() {
        String s ="[T]" + super.toString();
        return s;
    }
}