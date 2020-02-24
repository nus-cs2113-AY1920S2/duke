package alie.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected static final String DELIMITER = "|";

    public Task (String name) {
        this.description = name;
        this.isDone = false;
    }

    public String getName() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "O" : "X");
    }

    public Task setTaskCompleted(Task taskToSet) {
        taskToSet.isDone = true;
        return taskToSet;
    }

    public String getTaskInfo() {
        return ("[" + getStatusIcon() + "] " + description);
    }

    public abstract String encodeTask();
}