/**
 * Represents a Task in the list kept by Duke
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task with the given description
     * Default value for isDone is false
     * @param description of the Task created
     */
    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get the status of isDone of the Task and returns an icon
     * @return tick or cross icon
     */
    public String getStatusIcon(){
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    /**
     * Changes the boolean isDone to true
     */
    public void markAsDone(){
        this.isDone = true;
    }

    /**
     * Get the description of this Task
     * @return this Task's description
     */
    public String getDescription(){
        return description;
    }
}
