package TaskList;

/**
 * Represents a Task with description
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected char itemType;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the completion status of task through icon
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Set task as completed
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Prints the content of the task
     */
    public String printObject(){
        return "\0";
    }

    /**
     * Returns the string for saving to file
     */
    public String createStrForSaving() { return ""; }

    /**
     * Returns the converted format for saving to file
     */
    public String convertBoolean(){
        return (isDone == true) ? "1" : "0";
    }
}
