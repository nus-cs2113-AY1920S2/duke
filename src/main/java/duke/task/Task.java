package duke.task;

/**
 * represents information inside the list, has a
 * description of the task and allows one to get
 * the description and mark the task when done
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    public static final String TICK = "1";
    public static final String CROSS = "0";

    /**
     * Returns the task created with the specified
     * description.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    /**
     * Returns the tick or the cross icon of the specified tsk
     * If the task's isDone is false, a cross is returned, else its a tick
     *
     * @return either tick or cross
     */
    public String getStatusIcon() {
        if (isDone) {
            return TICK;
        } else {
            return CROSS;
        }
    }


    /**
     * returns the description of specified task
     *
     * @return description of task
     */
    public String getDescription() {
        return description;
    }


    /**
     * returns the additional information of each task,
     * 'by' for deadlines, 'at' for events and
     * none for todos
     *
     * @return either 'at', 'by' or null
     */
    public abstract String getExtra();


    /**
     * Sets the specified task's isDone to be true
     */
    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

}
