package duke.task;

/**
 * Represents a task.
 * It also functions as base class from which specialised tasks are inherited from.
 */
public class Task {


    private static final String DONE_SYMBOL = "/";
    private static final String NOT_DONE_SYMBOL = " ";

    /** Stores the description of the task */
    public String description;
    /** Denotes whether the task is done or not done */
    public boolean isDone;
    /** Used to denote type of task */
    public char taskType;

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
     * "/" is returned to denote that the task is complete.
     * " " is returned to denote that the task is incomplete.
     *
     * @return statusIcon Represents the current status of the task as described above.
     */
    public String getStatusIcon() {
        String statusIcon;
        statusIcon = isDone ? DONE_SYMBOL : NOT_DONE_SYMBOL;
        return statusIcon;
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
     * Sets the task's current status as requested by the user.
     *
     * @param isDone Represents the status to be set for the task as requested by the user.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Sets the task's current status as done.
     */
    public void markAsDone() {
        isDone = true;
    }


    /**
     * Returns both the status and description of the task.
     *
     * @return description Represents the description of the task.
     */
    public String getStatusWithDescription() {
        String statusWithDescription;
        statusWithDescription = "[" + this.getStatusIcon() + "] " + this.getDescription();
        return statusWithDescription;
    }

}
