package duke.tasks;

/**
 * Task is the public class responsible for storing information about the task.
 */

public class Task {

    /**
     * The type of task entered by the user.
     */

    protected String taskType;

    /**
     * The description of the task entered by the user.
     */

    protected String description;

    /**
     * The current done status of the task entered by the user.
     */

    protected boolean isDone;

    /**
     * Constructs the Task object.
     *
     * @param taskType    the type of task entered by the user.
     * @param description the description of the task entered by the user.
     */

    public Task(String taskType, String description) {
        this.taskType = taskType;
        this.description = description;
        this.isDone = false;
    }

    /**
     * Update the done status of the task.
     *
     * @param update the updated done status of the task.
     */

    public void setDone(boolean update) {
        this.isDone = update;
    }

    /**
     * Returns the done status of the task.
     *
     * @return the done status of the task.
     */

    public boolean getDone() {
        return this.isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return the description of the task.
     */

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the type of the task.
     *
     * @return the type of the task.
     */

    public String getTaskType() {
        return this.taskType;
    }

    /**
     * Returns the done status icon of the task.
     *
     * @return the done status icon of the task.
     */

    public String getStatusIcon() {
        return (getDone() ? "[/]" : "[X]"); //return tick or X symbols
    }

    /**
     * Returns the information related to the Task object.
     *
     * @return the information related to the Task object.
     */

    @Override
    public String toString() {
        return getTaskType() + " " + getStatusIcon() + " " + this.description;
    }

}
