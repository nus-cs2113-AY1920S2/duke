package duke.task;

/**
 * Abstract class representing Task.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Default constructor for abstract class Task.
     * @param description String containing the description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a unicode symbol depending on whether task is done.
     * @return Tick mark if it is done. Cross if it is not done
     */
    public String getStatusIcon() {
        if (isDone) {
            return ("[" + "\u2713" + "] ");
        } else {
            return ("[" + "\u2718" + "] ");
        }
    }

    /**
     * Formats tasks into a string.
     * @return String containing the status of task and description of task
     */

    @Override
    public String toString() {
        return getStatusIcon() + description;

    }

    /**
     * Marks task as done by setting isDone variable to true.
     */
    public void markAsDone() {
        isDone = true;
    }


}
