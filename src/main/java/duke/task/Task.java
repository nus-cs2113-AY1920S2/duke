package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    
    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }
    
    public void markAsDone() {
        this.isDone = true;
    }
    
    public String toString() {
        return getStatusIcon() + " " + description;
    }
    
    //Solution below adapted from https://www.baeldung.com/java-add-character-to-string
    protected String stringBuilder(String input) {
        StringBuilder sb = new StringBuilder(input);
        sb.insert(3, ":");
        return sb.toString();
    }
    
}
