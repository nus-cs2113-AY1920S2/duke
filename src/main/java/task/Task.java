package task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected static final String yesIcon = "[\u2713]";
    protected static final String noIcon = "[\u2718]";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? yesIcon : noIcon); //return tick or X symbols
    }

    public String getTaskDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + getTaskDescription();
    }
}