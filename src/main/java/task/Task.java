package task;

/**
 * A base class for 3 task types: Todo, Deadline and Event
 * Contains description and done status of task.
 *
 * @see Todo
 * @see Deadline
 * @see Event
 */

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns symbols corresponding to the done status
     * of the task.
     *
     * @return tick or X symbols.
     */
    public String getDescription() {return this.description;}

    public String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone() {
        this.isDone = true;
    }


    /**
     * Returns a string representation of task to be output to
     * the bot's UI.
     *
     * @return string of done status and description of task.
     * */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns a string representation of task to be written to
     * the storage file.
     *
     * @return string of done status and description of task.
     * */
    public String toFileString() {
        return this.getStatusIcon() + "," + this.description;
    }
}