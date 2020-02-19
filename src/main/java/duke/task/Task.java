package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Overloaded constructor in case you want to initialize a task as already done
    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the type of the task.
     *
     * @return The type of the task.
     */
    public String getType() {
        return "Task";
    }

    public String getStatusIcon() {
        return (isDone ? "/" : "X"); // Return / or X symbols
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    // Convert to comma-separated information
    // duke.csv file format:
    // taskID, taskType, taskIsDone, taskDesc, taskDate
    public String toData(int taskId) {
        String dataLine = taskId + ", Task";
        return dataLine;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
