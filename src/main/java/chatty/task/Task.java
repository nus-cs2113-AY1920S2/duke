package chatty.task;

/**
 * Tasks created by the user.
 */
public abstract class Task {

    private String description;
    private boolean isDone;

    /**
     * Constructor for task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets status icon of the task.
     *
     * @return Status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Gets done status of the task.
     *
     * @return Boolean value indicating whether or not the task is done.
     */
    public boolean getDoneStatus() {
        return this.isDone;
    }

    /**
     * Converts the task to string.
     *
     * @return String representing the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Converts the task to a string to be stored in file.
     *
     * @return String representing the task which should be stored in file.
     */
    public abstract String getFileString();
}
