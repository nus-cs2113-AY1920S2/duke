package duke.task;

/**
 * Represents a task.
 */
public class Task {

    /** Description of the task */
    protected String description;

    /** Completion status of the task */
    protected boolean isCompleted;

    public Task(String description) {
        this.description = description;
        isCompleted = false;
    }

    /**
     * Gets the status of the task.
     *
     * @return Status of the task.
     */
    public String getStatus() {
        if (isCompleted) {
            return "Complete";
        } else {
            return "Not complete";
        }
    }

    /**
     * Gets the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isCompleted = true;
    }

    /**
     * Defines the string format of <code>Task</code> object.
     *
     * @return String format of <code>Task</code> object.
     */
    @Override
    public String toString() {
        return "[" + getStatus() + "] " + description;
    }
}
