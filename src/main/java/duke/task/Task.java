package duke.task;

/**
 * Task to be completed.
 */
public class Task {

    /** Task description. */
    protected String description;

    /** Boolean indicating whether task is done or not. */
    protected boolean isDone;

    /**
     * Constructs a task object.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a task object.
     *
     * @param isDone Marks whether object is already done or not.
     * @param description Description of task.
     */
    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the type of the task.
     *
     * @return The type of the task.
     */
    public String getType() {
        return "Task";
    }

    /**
     * Returns status icon of task depending on value of isDone.
     *
     * @return Status icon depending on value of isDone.
     */
    public String getStatusIcon() {
        return (isDone ? "/" : "X"); // Return / or X symbols
    }

    /**
     * Returns task description.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether the task is done or not.
     *
     * @return Whether the task is done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Converts the Task object to data representation to be stored in a data file.
     * File format:
     * taskId, taskType, taskIsDone, taskDesc
     *
     * @param taskId ID of task.
     * @return String representing the Task object in comma-separated data format.
     */
    public String toData(int taskId) {
        String dataLine = taskId + ", Task";
        return dataLine;
    }

    /**
     * Represents the input task as a string.
     *
     * @return String representing the Task object.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
