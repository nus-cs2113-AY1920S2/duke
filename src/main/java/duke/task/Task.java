package duke.task;

/**
 * Represents a task in the task list
 */
public class Task {
    public String description;
    public boolean isDone;

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
     * Method to get the status icon to represent if the task has been done.
     * @return tick or cross symbol
     */
    public String getStatusIcon() {
        return (isDone ? "[" + "\u2713" + "]" : "[" + "\u2718" + "] "); //return tick or X symbols
    }

    /**
     * Method to mark the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Method to format the tasks in the task list.
     * @return formatted task
     */
    public String formatResult() {
        return isDone + "|" + this.description;
    }

    @Override
    public String toString() {
        return getStatusIcon() + description;
    }
}