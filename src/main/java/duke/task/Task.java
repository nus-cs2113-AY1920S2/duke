package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;
    private static int taskCount = 0;
    protected final String PIPE = " | ";
    
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        taskCount += 1;
    }
    
    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }
    
    public void markAsDone() {
        this.isDone = true;
    }
    
    public static int getTaskCount() {
        return taskCount;
    }
    
    public String toString() {
        return getStatusIcon() + " " + description;
    }
    
    public String toStorage() {
        return PIPE + (isDone ? "1" : "0") + PIPE + description;
    }
    
}
