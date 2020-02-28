package duke.tasks;

/**
 * Represents a task with a description of the task to be
 * done and a boolean field which indicates whether the
 * task is done or not.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor to create a new Task.
     *
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Method to check whether the task has been completed.
     *
     * @return true if it has been marked done, false otherwise
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Method to get the description of the task.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method to get the tick or cross when printing. A tick represents
     * that the task has been done and a cross represents that it has not.
     *
     * @return tick or X symbol
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Method to mark a task as completed.
     */
    public void markAsDone(){
        this.isDone = true;
    }

    /**
     * Format which the tasks are saved into the .txt file.
     *
     * @return String with the save format
     */
    public String saveFormat() {
        return String.valueOf(isDone) + "//" + this.description;
    }

    /**
     * Format which the tasks are printed.
     *
     * @return String with the print format
     */
    @Override
    public String toString() {
        return '[' + this.getStatusIcon() + "] " + this.description;
    }
}
