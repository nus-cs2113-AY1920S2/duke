
public class Task {

    /** Completion status for the task **/
    private boolean isDone;

    /** Task description given by the user**/
    private String description;

    public Task (String description) {
        this.description = description;
        isDone = false;
    }

    public String getDescription () {
        return description;
    }

    /**
     * Gets the status icon for the task
     *
     * @return Label with a cross or check mark
     */
    public String getStatusIcon () {
        return "[" + (isDone ? "\u2713" : "\u2718") + "]";
    }

    public boolean getCompletionStatus () {
        return isDone;
    }

    public void setTaskAsDone () {
        this.isDone = true;
    }

}
