package duke;

/**
 * This class represents a task. It contains a description and can be marked as
 * done.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * This is the constructor of the task. Tasks are not marked as done when
     * first created.
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * This method returns either a 1, or a 0 as a string, which is used to
     * mark a task as done or not.
     * @return Status of task.
     */
    public String getStatusIcon() {
        return (isDone ? "1" : "0"); //"1" for done, "0" for not done
    }

    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * This method gives a string representation of the task.
     * @return String representing the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
