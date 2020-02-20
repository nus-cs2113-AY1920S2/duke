package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (getIsDone() ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    // Not immutable version
    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
