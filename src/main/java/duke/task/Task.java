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

    /**
     * Returns the description of the task.
     *
     * @return description Represents the description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status of the task in form of an icon.
     * "\u2713" is returned to denote that the task is complete.
     * "\u2718" is returned to denote that the task is incomplete.
     *
     * @return statusIcon Represents the current status of the task as described above.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }


    /**
     * Update the task's current status done.
     */
    public void updateTask() {
        this.isDone = true;
    }

    /**
     * Abstract method for child class to overwrite.
     */
    public abstract String saveTask();

    /**
     * Returns both the status and description of the task.
     *
     * @return s Represents the status and description of the task.
     */
    public String toString() {
        String s = "[" + this.getStatusIcon() + "] " + description;
        return s;
    }
}
