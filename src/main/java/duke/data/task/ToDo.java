package duke.data.task;

/**
 * Represents a ToDo task.
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo Task Class.
     * <p> <br>
     * It creates a new ToDo task with the description provided by the user.
     *</p>
     * @param description Description of the task to be created.
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
    public String toOutput() {
        String output = "T | " + (isDone ? '1' : '0') + " | " + description;
        return output;
    }


    /**
     * Returns both the status and description of the task.<br>
     * Overrides the corresponding method in the parent class.
     *
     * @return text Represents the description of the task.
     */
    @Override
    public String toString() {
        String text = "[T]" + super.toString();
        return text;
    }
}
