package duke.task;

/**
 * Represents a parent class for all tasks in Duke.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructor for parent class.
     * Sets the isDone for all tasks to be false.
     *
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void updateTask() {
        this.isDone = true;
    }

    public abstract String saveTask();

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
