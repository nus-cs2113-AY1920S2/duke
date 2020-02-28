package duke.tasks;

/**
 * Represents an absract class Task that has subclass tasks associaed with
 * deadlines, events and todos.
 */
public abstract class Task {

    protected String description;
    public boolean isDone;

    /**
     *  Returns the description associated with a task.
     *
     * @return description Descripton of what the task is and any date associated with it.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Constructor that helps initialize a task
     *
     * @param description Descripton of what the task is and any date associated with it.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /** Marks a task as done.*/
    public void markIt() {
        this.isDone = true;
    }

    /** Returns the Status Icon, check mark or cross mark, representing of the task is done.  */
    public String getStatusIcon() {
        //return tick or X symbols
        return (isDone ? "\u2713" : "\u2718");
    }

    /** Represents the icon that represents the task, eg. [T], [E], [D].  */
    public String getTypeIcon() {
        return null;
    }

}
