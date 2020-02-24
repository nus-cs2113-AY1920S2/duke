package Tasks;

/**
 * Represents a Task object for tasks
 * AnTask object corresponds to a description, and boolean isDone to check if task has been completed
 */
public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task (String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns boolean status of task
     * @return true if task is completed ; false otherwise
     */
    public boolean getStatus() {
        return isDone;
    }

    /**
     * Returns status icon of Task object based on its status
     * @return status icon
     */
    public String getStatusIcon() {
        return (isDone ? "/" : "x"); //return tick or X symbols
    }

    /**
     * Returns description of task
     * @return description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Mark Task object as completed
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns String format for tasks to be printed on CLI
     * @return String format of tasks
     */
    public String toString() {
        return "[ " + getStatusIcon() + " ] " + getDescription();
    }

    /**
     * Returns String format for Task tasks to be stored in text file
     * @return String format of Task tasks
     */
    public String storeText() {
        return isDone + "," + getDescription();
    }
}