package duke.task;

public abstract class Task {
    protected static final String DELIMITER = "|";
    protected static final String TASK_DONE_ICON = "✓";
    protected static final String TASK_NOT_DONE_ICON = "✘";

    protected String description;
    protected boolean isDone;

    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? TASK_DONE_ICON : TASK_NOT_DONE_ICON);
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public abstract String encodeTask();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
