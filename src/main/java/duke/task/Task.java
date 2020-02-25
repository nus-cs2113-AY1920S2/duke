package duke.task;

/**
 * Represent the Task object
 */
public abstract class Task {
    
    protected String description;
    protected boolean isDone;
    protected final String PIPE = " | ";
    
    /**
     * Constructor for Task
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    
    /**
     * Get the status of the task, completed or uncompleted
     *
     * @return the status of the task
     */
    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }
    
    /**
     * Marks the task as completed
     */
    public void markAsDone() {
        this.isDone = true;
    }
    
    /**
     * Return the information as a string
     *
     * @return the string of information relevant to the object
     */
    public String toString() {
        return getStatusIcon() + " " + description;
    }
    
    /**
     * Return the information with a specific format to the storage
     *
     * @return the string of information relevant to the object
     */
    public String toStorage() {
        return PIPE + (isDone ? "1" : "0") + PIPE + description;
    }
    
}
