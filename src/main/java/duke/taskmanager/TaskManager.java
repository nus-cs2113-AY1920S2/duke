package duke.taskmanager;

public class TaskManager {
    protected String task;
    protected boolean isDone;
    public TaskManager(String task) {
        this.task = task;
        this.isDone = false;
    }
    public String getTaskStatus() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
    public void markAsDone() {
        isDone = true;
    }
    @Override
    public String toString() {
        return "[" + getTaskStatus() + "]" + task;
    }
}
