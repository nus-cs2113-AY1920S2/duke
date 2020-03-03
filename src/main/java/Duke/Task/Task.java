package duke.task;

/**
 * Class representing a task.
 */
public class Task {
    
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks the task status.
     *
     * @return isDone
     */
    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Checks the task status.
     */
    public String getTaskStatus() {
        return (isDone ? "Y" : "N"); //return tick or X symbols
    }

    /**
     * Retrieves the Task Description.
     * @return description
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + getTaskStatus() + "]" + description;
    }
    
}
