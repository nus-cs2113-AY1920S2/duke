public class Task {
    protected String description;
    protected boolean isDone;

    /** Constructor */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get the status icon of the task, finish or not
     *
     * @return Status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); // return tick or X symbols
    }

    /**
     * Task description getter
     *
     * @return Task content
     */
    public String getDescription() {
        return description;
    }

    /** Mark the task as done */
    public void markAsDone() {
        isDone = true;
    }
}
