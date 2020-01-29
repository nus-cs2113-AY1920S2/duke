import javax.print.DocFlavor;

/**
 * Represents a Task in the list kept by Duke
 */
public class Task {
    protected String description;
    protected boolean isDone;
    private static int numberOfTasksInList;
    /**
     * Creates a new Task with the given description
     * Default value for isDone is false
     * @param description of the Task created
     */
    public Task(String description){
        this.description = description;
        this.isDone = false;
        numberOfTasksInList++;
    }

    public static int getNumberOfTasksInList() {
        return numberOfTasksInList;
    }

    /**
     * Get the status of isDone of the Task and returns an icon
     * @return tick or cross icon
     */
    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    /**
     * Changes the boolean isDone to true
     */
    public void markAsDone(){
        this.isDone = true;
    }

    public String getDescriptionInListFormat(){
        return String.format("%s %s", getStatusIcon(), getDescription());
    }

    /**
     * Print done response message
     */
    public String getDoneResponseMessage(int itemIndexRequested){
        return String.format("[%d. %s] marked as done!", itemIndexRequested, getDescription());
    }

    /**
     * get description of Task
     * @return description of Task
     */
    public String getDescription() {
        return description;
    }
}
