package src.main.java;

public class Task {
    protected String description;
    protected boolean isDone;
    static int totalNumberOfTask = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        totalNumberOfTask++;
    }

    public String getStatusIcon() {
        return (isDone? "\u2713": "\u2718");
    }

    public void completedTask() {
        this.isDone = true;
    }
}
