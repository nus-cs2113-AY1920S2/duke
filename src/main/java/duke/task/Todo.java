package duke.task;

/**
 * Simple task that can be marked as done.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task.
     *
     * @param description Description of task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a Todo task.
     *
     * @param isDone Marks whether object is already done or not.
     * @param description Description of task.
     */
    public Todo(boolean isDone, String description) {
        super(isDone, description);
    }

    /**
     * Returns the type of the task.
     *
     * @return The type of the task.
     */
    @Override
    public String getType() {
        return "T";
    }

    /**
     * Converts the Task object to data representation to be stored in a data file.
     * File format:
     * taskId, taskType, taskIsDone, taskDesc
     *
     * @param taskId ID of task.
     * @return String representing the Task object in comma-separated data format.
     */
    @Override
    public String toData(int taskId) {
        String dataLine = taskId + "," + this.getType() + "," + this.isDone() + "," + this.getDescription();
        return dataLine;
    }

    /**
     * Represents the input task as a string.
     *
     * @return String representing the Task object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}