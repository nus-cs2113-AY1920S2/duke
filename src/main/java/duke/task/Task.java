package duke.task;

/**
 * The Task class keeps a record of the description of any Task and whether is is done.
 * @author Lam Yue Wei
 * @version CS2113 AY19/20 Sem 2 Duke
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Public constructor for Task and set isDone to false by default.
     * @param description Description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Getter method for the status of Task's completion.
     * @return Y if Task is done and N if Task is not done.
     */
    public String getStatusIcon() {
        return (getIsDone() ? "Y" : "N");
    }

    // Not immutable version

    /**
     * Mark the task as done by setting isDone to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Getter method for description of the Task.
     * @return Description of the Task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Getter method for isDone.
     * @return isDone which is true if Task if done and false if Task is not done.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Return a String representation of this Task.
     * @return The Task's done status, followed by the description.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
