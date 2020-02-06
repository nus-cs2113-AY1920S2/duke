package src.main.java;

public class Task {
    static int totalNumberOfTask = 0;
    protected String description;
    protected String taskType;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        totalNumberOfTask++;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void completedTask() {
        this.isDone = true;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

