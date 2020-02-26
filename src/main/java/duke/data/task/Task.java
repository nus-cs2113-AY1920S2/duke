package duke.data.task;

/**
 * Represents a task.
 * It also functions as base class from which specialised tasks are inherited from.
 */
public abstract class Task {

    /**
     * Stores the description of the task.
     */
    protected String description;
    /**
     * Denotes whether the task is done or not done.
     */
    protected boolean isDone;

    /**
     * Constructor for Task Class.
     * It creates a new task with the description provided by the user.
     * It also sets isDone to false as it is a newly created task.
     *
     * @param description Description of the task to be created.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of the task in form of an icon.
     * "\u2713" is returned to denote that the task is complete.
     * "\u2718" is returned to denote that the task is incomplete.
     *
     * @return statusIcon Represents the current status of the task as described above.
     */
    public String getStatusIcon() {
        String statusIcon = isDone ? "\u2713" : "\u2718";
        return statusIcon; //return tick or X symbols
    }

    /**
     * Sets the task's current status done.
     */
    public void taskDone() {
        this.isDone = true;
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
     * Abstract method for Inherited class to overwrite.
     */
    public abstract String toOutput();


    /**
     * Returns both the status and description of the task.
     *
     * @return text Represents the status and description of the task.
     */
    @Override
    public String toString() {
        String text = "[" + getStatusIcon() + "] " + description;
        return text;
    }
}
