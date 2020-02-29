package data;

import java.util.ArrayList;

/**
 * This class is the superclass of all tasks that can be stored in Duke.
 * <p></p>
 * <p>This superclass is made abstract so the tasks stored in Duke will definitely be one of the subclasses of tasks</p>
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected final String TASK_DONE_NOTATION = "1";
    protected final String TASK_NOTDONE_NOTATION = "0";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription(){
        return this.description;
    }

    public boolean getIsDone(){
        return this.isDone;
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * This abstract method is overridden in the Task subclasses. It converts the data for Task objects into
     * a String array for easy parsing and application.
     * <p></p>
     * <p>This data is used primarily by the Storage class when saving the task into the local save file.</p>
     * <p></p>
     * <p>View the subclasses to see how the method is overridden.</p>
     * @return the subclass will return its respective task information
     * @see Todo
     * @see Event
     * @see Deadline
     */
    public abstract String[] getTaskData();

    public abstract void addIfContainsKeyword(ArrayList<Task> searchResults, String searchKeyword);


}
