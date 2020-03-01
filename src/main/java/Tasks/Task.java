package Tasks;

/**
 * Superclass for <code>ToDo</code>, <code>Deadline</code> and <code>Event</code>.
 *
 * @see ToDo
 * @see Deadline
 * @see Event
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Class constructor of <code>Task</code> class.
     * Initialises done status to false.
     * @param description Description of <code>Task</code>.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns symbol based on done status.
     * @return "x" if task is done, " " if task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "x" : " "); // Return x if task is done
    }

    /**
     * Return task description.
     * @return Description of <code>Task</code>
     */
    public String getTask() {
        return description;
    }

    /**
     * Set done status of <code>Task</code>.
     * @param isDone Done status to be set.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns a <code>String</code> of the <code>Task</code> to be printed.
     * @return <code>String</code> of done status and description of <code>Task</code>.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getTask();
    }
    /**
     * Returns a <code>String</code> of the <code>Task</code> to be written to the text file.
     * @return <code>String</code> of done status and description of <code>Task</code>.
     */
    public String toFileString() {
        return this.getStatusIcon() + "," + this.description;
    }
}