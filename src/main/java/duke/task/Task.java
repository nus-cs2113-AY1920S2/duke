package src.main.java.duke.task;

public class Task {
    protected static int totalNumberOfTask = 0;
    protected String description;
    protected String taskType;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        totalNumberOfTask++;
    }

    public static int getTotalNumberOfTask() { return totalNumberOfTask; }

    public static void reduceTotalNumberOfTask() {
        totalNumberOfTask -= 1;
    }

    public String getStatusIcon() {
        return (isDone ? "/" : "x");
    }

    public void completedTask() {
        isDone = true;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public boolean getIsDone() { return isDone; }

    public String writeInFile() {
        String taskStatus = (isDone ? "1" : "0");
        return taskType + " | " + taskStatus + " | " + description;
    }

    public String getDescription() { return description; }
}

