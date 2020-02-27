package src.main.java.duke.task;

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

    public static int getTotalNumberOfTask() { return totalNumberOfTask; }

    public static int reduceTotalNumberOfTask() {
        totalNumberOfTask -= 1;
        return totalNumberOfTask;
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

    public boolean getIsDone() {return isDone;}
}

