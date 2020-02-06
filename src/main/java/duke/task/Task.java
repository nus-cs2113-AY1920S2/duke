package duke.task;

public class Task {
    protected int taskID;
    protected String description;
    protected boolean isDone;

    public Task (int taskID, String description, boolean isDone) {
        this.taskID = taskID;
        this.description = description;
        this.isDone = isDone;
    }

    public void setStatus () {
        this.isDone = true; // mark as done
    }

    public String getStatusIcon () {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    public String getTaskType () {
        return "[]";
    }

    @Override
    public String toString () {
        return (taskID + 1) + ". " + getStatusIcon() + " " + description;
    }
}
