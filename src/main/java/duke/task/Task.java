package duke.task;

/**
 * Structure for a task.
 */

public class Task {
    public int taskID;
    protected String description;
    protected boolean isDone;

    public Task (int taskID, String description, boolean isDone) {
        this.taskID = taskID;
        this.description = description;
        this.isDone = isDone;
    }

    /** Mark the task as done. **/
    public void setStatus () {
        this.isDone = true; // mark as done
    }

    /**Gets the status icon of task.
     * @return status icon.
     */
    public String getStatusIcon () {
        return (isDone ? "[v]" : "[x]"); //return tick or X symbols
    }

    public String getTaskType () {
        return "[]";
    }

    @Override
    public String toString () {
        return (taskID + 1) + ". " + getStatusIcon() + " " + description;
    }
}
