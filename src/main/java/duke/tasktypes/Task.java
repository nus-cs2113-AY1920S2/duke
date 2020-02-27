package duke.tasktypes;

/**
 * The superclass of all the possible tasks that is stored in duke
 * <p></p>
 * <p>This superclass is made abstract so as to prevent any tasks to be made to be just a superclass task. It forces the
 * tasks made to be one of the subclasses of tasks</p>
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * This method gets overridden by its subclass. It returns the task's information in a nicely packaged
     * <code>String[]</code> data structure that can be easily used
     * <p></p>
     * <p>Used by the {@link duke.Storage} <code>save()</code> function to save the task into the offline saved
     * data</p>
     * <p></p>
     * <p>See the respective subclasses for more details</p>
     * @return the subclass will return its respective task information
     * @see Todo
     * @see Event
     * @see Deadline
     * @see duke.Storage#save
     */
    public abstract String[] getTaskInfo();

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description + " ";
    }


}
