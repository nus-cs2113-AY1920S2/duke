package duke.task;

/**
 * Represents a task or piece of work that the user inputs, and can be of type: todo,
 * event, or deadline. Tasks contain their type, mark of whether it has been completed, and
 * description of what needs to be done.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return ("[" + (isDone ? "\u2713" : "\u2718") + "] "); //return tick or X symbols
    }

    public void markAsDone() { 
    	this.isDone = true; 
    }
    public String toString(){
        return (this.getStatusIcon() + description);
    }
}
