package duke.taskmanager;

public class TaskManager {
    public String task;
    protected boolean isDone;
    public TaskManager(String task) {
        this.task = task;
        this.isDone = false;
    }
    public String getTaskStatus() {
        return (isDone ? "Done" : "Not Done"); //return whether it's done
    }
    public void markAsDone() {
        isDone = true;
    }
    @Override
    public String toString() {
        return "[" + getTaskStatus() + "] " + task;
    }
}
