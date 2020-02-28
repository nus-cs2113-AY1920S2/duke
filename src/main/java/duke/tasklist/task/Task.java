package duke.tasklist.task;

import java.time.LocalDate;

/**
 * Stores a task.
 * Since there is no task without type, this super class is an abstract class.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(){
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        /** Return tick or X symbols. */
        return (isDone ? "/" : " ");
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getTime() {
        return null;
    }

    public void markAsDone() {
        isDone = true;
    }

    /**
     * Prints a task appropriately.
     *
     * @return a string.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Stores a task according to back up file standard.
     *
     * @return a string.
     */
    public String toFile() {
        return ((isDone ? "1" : "0") + " | " + this.description);
    }
}
