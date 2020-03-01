package duke.tasks;

public class Task {

    protected String taskType;
    protected String description;
    protected boolean isDone;

    public Task(String taskType, String description) {
        this.taskType = taskType;
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean update) {
        this.isDone = update;
    }

    public boolean getDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTaskType() {
        return this.taskType;
    }

    public String getStatusIcon() {
        return (isDone ? "[/]" : "[X]"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return getTaskType() + " " + getStatusIcon() + " " + this.description;
    }

}
