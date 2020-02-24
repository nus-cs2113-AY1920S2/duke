package duke.task;

public class Todo extends Task {
    
    private final String PREFIX = "T";
    
    public Todo(String description) {
        super(description);
    }
    
    @Override
    public String toString() {
        return "[" + PREFIX + "]" + super.toString();
    }
    
    @Override
    public String toStorage() {
        return PREFIX + super.toStorage();
    }
    
}
