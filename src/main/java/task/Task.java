package task;

/**
 * Represents a task specified by the user.
 * This class is inherited by Event, Deadline and Todo.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    /**
     * Constructor for Task class.
     * <p> <br>
     * Creates a new Task with the task description.
     * Initializes the completion status of the task to false, and the taskType to a placeholder "Task".
     *</p>
     * @param description Description of the task provided by the user.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = "Task";
    }

    public void setDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns / if task has been marked as completed, or X otherwise.
     *
     * @return Tick symbol if completed, cross symbol otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "/" : "X");
    }

    /**
     * Returns String in the format used for recording tasks in the .txt file.
     *
     * @return File record.
     */
    public String getFileRecord() {
        int doneValue = isDone ? 1 : 0;
        return String.format("%s,%d,%s\n", this.taskType, doneValue, this.description.strip());
    }

    /**
     * Returns basic re-formatted task description for displaying of task details.
     * This method is overridden in the child classes to display information about specific types of tasks.
     *
     * @return String of re-formatted task details.
     */
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
