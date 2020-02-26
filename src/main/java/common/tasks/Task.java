package common.tasks;

public class Task {
    protected final String description;
    protected boolean isDone;
    
    /**
     * Constructor for Task class.
     * 
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    
    public String getDescription() {
    	return this.description;
    }
    
    /**
     * Returns status icon depicting if task is done or not.
     * 
     * @return Indication if task is done or not.
     */
    public String getStatusIcon() {
        return (isDone ? "Y" : "N"); //return tick or X symbols
    }
    
    /**
     * Sets task as done.
     * 
     * @return void.
     */
    public void markAsDone() {
        this.isDone = true;
    }
    
    public boolean isDone() {
    	return this.isDone;
    }
    
    /**
     * Returns String representation of Task.
     * 
     * @return String representation of Task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}