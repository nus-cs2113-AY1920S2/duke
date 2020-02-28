package common.tasks;

/**
 * Todo subclass of a task.
 */
public class ToDo extends Task {
    
    public ToDo(String description) {
        super(description);
    }
    
    public ToDo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }
    
    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + this.description;
    }
}
