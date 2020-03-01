package duke.task.tasktypes;

/**
 * A class representing a Task. It can either a {@link Todo}, a {@link Deadline}, or
 * an {@link Event}.
 */
public abstract class Task {

    /** Check and cross markers */
    private static final String checkMark = "\u2713";
    private static final String crossMark = "\u2718";

    /** Completion status for the task */
    protected boolean isDone;

    /** Description given by the user */
    protected String description;

    protected TaskType taskType;

    public enum TaskType {
        E, T, D;
    }

    public Task (String description) {
        this.description = description;
        isDone = false;

    }

    /**
     * Gets the status icon for the task.
     *
     * @return Label with a cross or check mark.
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
