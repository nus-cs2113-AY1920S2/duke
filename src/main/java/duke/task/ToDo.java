package duke.task;

/**
 * Represents a ToDo task.
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo Task Class.
     * <p> <br>
     * It creates a new ToDo task with the description provided by the user.
     * It also sets the taskType to 'T', denoting that the task is a ToDO task.
     *</p>
     * @param description Description of the task to be created.
     */
    public ToDo(String description) {
        super(description);
        taskType = 'T';
    }

    /**
     * Returns both the status and description of the task.<br>
     * Overrides the corresponding method in the parent class.
     *
     * @return description Represents the description of the task.
     */
    @Override
    public String getStatusWithDescription() {
        return "[" + this.taskType + "]" + super.getStatusWithDescription();
    }

}
