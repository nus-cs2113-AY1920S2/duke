package task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = "Task";
    }

    public void setDone() {
        this.isDone = true;
    }

    /** Returns tick or X based on task completion */
    public String getStatusIcon() {
        // Return tick or X symbols
        return (isDone ? "\u2713" : "\u2718");
    }

    /** Returns string used for storing task in txt file */
    public String getFileRecord() {
        int doneValue = isDone ? 1 : 0;
        return String.format("%s,%d,%s\n", this.taskType, doneValue, this.description.strip());
    }

    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
