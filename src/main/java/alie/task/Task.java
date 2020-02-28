package alie.task;

/**
 * Task that user keeps track of.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected static final String DELIMITER = "|";

    /**
     * Default constructor for a task to initialise task's name and mark that it is not done.
     * @param name String containing Name of the task.
     */
    public Task (String name) {
        this.description = name;
        this.isDone = false;
    }

    /**
     * Getter to obtain the name of the Task added.
     * @return String containing Name of the task.
     */
    public String getName() {
        return this.description;
    }

    /**
     * Getter to check whether the task is done
     * @return "O" if task is done and "X" if task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "O" : "X");
    }

    /**
     * Setter to set a task as completed.
     * @param taskToSet The task that is completed.
     * @return Same task that is now completed.
     */
    public Task setTaskCompleted(Task taskToSet) {
        taskToSet.isDone = true;
        return taskToSet;
    }

    /**
     * Getter to obtain all the information of the task.
     * @return A string containing all information of the task in a specific format.
     */
    public String getTaskInfo() {
        return ("[" + getStatusIcon() + "] " + description);
    }

    /**
     * Formats information in a task for it to be saved and decoded in future.
     * @return String with all information in the task.
     */
    public abstract String encodeTask();
}