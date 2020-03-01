package duke.task.tasktypes;

public abstract class Task {

    /** Check and cross markers */
    private static final String checkMark = "\u2713";
    private static final String crossMark = "\u2718";

    /** Completion status for the task **/
    protected boolean isDone;

    /** duke.task.tasktype.Task description given by the user**/
    protected String description;

    public enum TaskType {
        E, T, D;
    }

    protected TaskType taskType;

    public Task (String description) {
        this.description = description;
        isDone = false;

    }

    /**
     * Gets the status icon for the task
     *
     * @return Label with a cross or check mark
     */
    protected String getStatusIcon () {
        return "[" + (isDone ? checkMark : crossMark) + "]";
    }

    public boolean getCompletionStatus () {
        return isDone;
    }

    public void setTaskAsDone () {
        this.isDone = true;
    }

    public String getDescription () {
        return description;
    }

    @Override
    public String toString() {
        return String.format("%s %s", getStatusIcon(), description);
    }
}
