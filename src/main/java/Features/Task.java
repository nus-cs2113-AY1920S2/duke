package Features;

/**
 * Base class for the 3 specific task type: <code>Event Deadline Todo</code>.
 * Contain methods to check the elements of each task object.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String timeToComplete;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getTimeToComplete() {
        return this.timeToComplete;
    }
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
    public String getDescription() {
        return this.description;
    }
    public String getType() {return "Task";}
    public void setIsDone(boolean isDone) {
       this.isDone = isDone;
    }
    public boolean getIsDone() {return isDone;}
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
