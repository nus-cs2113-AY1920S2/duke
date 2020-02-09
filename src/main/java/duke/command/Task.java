package duke.command;

public class Task {
    protected String description;
    protected boolean isDone;
    protected static int totalTasks = 0;
    protected String taskDescription;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        taskDescription = "task";
        totalTasks++;
    }

    public String getStatusIcon() {
        if (isDone) {
            return ("[" + "\u2713" + "] ");
        } else {
            return ("[" + "\u2718" + "] ");
        }
    }

    @Override
    public String toString() {
        return getStatusIcon() + description;

    }

    public void markAsDone() {
        isDone = true;
    }

    public int getTotalTasks() {
        return totalTasks;
    }

    public String getTaskDescription() {
        return taskDescription;
    }


}
