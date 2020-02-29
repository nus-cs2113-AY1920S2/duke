package task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected int num;

    private static int curr = 1;

    /**
     * Constructor for Task class
     * Sets isDone to false
     *
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.num = curr;
        curr++;
    }

    /**
     * Returns the status of the task in form of an icon.
     * "\u2713" is returned to denote that the task is complete.
     * "\u2718" is returned to denote that the task is incomplete.
     *
     * @return statusIcon Represents the current status of the task as described above.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Get the description of the task
     *
     * @return description of task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of task
     *
     * @param description The modified description of the task
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the integer value of boolean value isDone
     *
     * @return 1 if isDone is true, 0 otherwise
     */
    public int isDoneNum() {
        if (isDone) {
            return 1;
        }
        return 0;
    }

    /**
     * Set the isDone value to the input
     *
     * @param done Boolean value to be set to isDone
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Returns the status and the description as a string
     *
     * @return String of status and description of task
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    /**
     * The description of the task to be saved
     *
     * @return Description of task
     */
    public String saveTask() {
        return description;
    }
}
