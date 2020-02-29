package types;

/**
 * Represents a single task, which can be a to do, event, or deadline
 */
public class Task {

    /**
     * Name of task
     */
    private String name;

    /**
     * Whether task is done or not
     */
    private boolean isDone;

    /**
     * A types.Task object: something to be done
     * @param name name of task
     */
    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    /**
     * Set task to done or not
     * @param isDone whether task is done or not
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns if task is done
     * @return if task is done
     */
    public int getDone() {
        if (isDone) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Get name of task
     * @return types.Task name
     */
    public String getName() {
        return name;
    }

    /**
     * Print task is specified format
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + name;
    }

    /**
     * Get status icon of task
     * @return status icon of task
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Get type of task
     * @return type
     */
    public String getType() {
        return "Z";
    }

    /**
     * Returns when the event is
     * @return when event is at
     */
    public String getAt() {
        return "";
    }

    /**
     * Returns when deadline is by
     * @return when deadline is by
     */
    public String getBy() {
        return "";
    }

}
