public class Task {
    private final String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "✓" : "✗"); //return tick or X symbols
    }
    
    public void markAsDone() {
    	this.isDone = true;
    }
    
    @Override
    public String toString() {
    	return "[" + this.getStatusIcon() + "] " + this.description;
    }
}