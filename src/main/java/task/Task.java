package task;

/**
 * Represents a task specified by the user.
 * A Task object contains its task description, completion status, and letter code for its task type.
 */
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

    /**
     * Returns tick if task has been marked as completed, or X otherwise.
     * @return Tick symbol if completed, cross symbol otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Returns String in the format used for recording tasks in the .txt file.
     * @return File record.
     */
    public String getFileRecord() {
        int doneValue = isDone ? 1 : 0;
        return String.format("%s,%d,%s\n", this.taskType, doneValue, this.description.strip());
    }

    /**
     * Returns re-formatted task description for displaying of task details.
     * @return String of re-formatted task details.
     */
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
