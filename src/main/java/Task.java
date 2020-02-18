/**
 * Represents tasks in Duke
 * A task object corresponds to the description and a boolean to check if task is completed
 */

public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task (String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns status of task object
     * @return isDone
     */
    public boolean getStatus() {
        return isDone;
    }

    /**
     * Returns status icon based on status of task object
     * @return status icon
     */
    public String getStatusIcon() {
        return (isDone ? "/" : "x"); //return tick or X symbols
    }

    /**
     * Returns description of task object
     * @return description of task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks Task object as completed
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns string format for displaying on CLI
     * @return  String representation for Task object
     */
    public String toString() {
        return "[ " + getStatusIcon() + " ] " + getDescription();
    }

    /**
     * Returns the Task object's details , separated by commas
     * @return String format to be stored in text file
     */
    public String storeText() {
        return isDone + "," + getDescription();
    }
}