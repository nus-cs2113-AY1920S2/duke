public class Task {
    protected String description;
    protected boolean isDone;
    public static final String TICK = "\u2713";
    public static final String CROSS = "\u2718";

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
