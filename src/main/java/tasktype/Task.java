package tasktype;

import java.util.ArrayList;

/**
 * This class is the superclass of all tasks that can be stored in data.Duke.
 * <p></p>
 * <p>This superclass is made abstract so the tasks stored in data.Duke will definitely be one of the subclasses of tasks</p>
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
        return (this.isDone ? "/" : " "); //return tick or X symbols
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
     * This abstract method is overridden in the subclasses of Task. It converts the data for Task objects into
     * a String array for easy parsing and application.
     * <p></p>
     * <p>This data is used primarily by the storage.Storage class when saving the task into the local save file.</p>
     * <p></p>
     * <p>View the subclasses to see how the method is overridden.</p>
     * @return the subclass will return its respective task information
     * @see Todo
     * @see Event
     * @see Deadline
     */
    public abstract String[] getTaskData();

    /**
     * This abstract method is overridden in the subclasses of Task. It adds the current Task object to a searchResults
     * ArrayList if the Task's description or field contains the keyword.
     * <p></p>
     * <p>This method is primarily used for the FIND command.</p>
     * <p>View the subclasses to see how the method is overridden.</p>
     * @param searchResults a list of Tasks containing the search keyword
     * @param searchKeyword the keyword to be searched for in the Task
     */
    public abstract void addIfContainsKeyword(ArrayList<Task> searchResults, String searchKeyword);


}
