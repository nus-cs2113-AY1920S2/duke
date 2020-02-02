public class Task {
    protected String description;
    protected boolean isDone;

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
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Sets the specified task's isDone to be true
     */
    public void markAsDone() {
        isDone = true;
    }
}
