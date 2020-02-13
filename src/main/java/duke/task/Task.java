package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getType() {
        return "Task";
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); // Return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    // Provides a string to be stored in data file
    public String toData(int taskId) {
        String dataLine = taskId + ", Task";
        return dataLine;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
