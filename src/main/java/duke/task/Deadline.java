package duke.task;

public class Deadline extends Task {
    
    private String by;
    private final String PREFIX = "D";
    private final int startIndexForSubstring = 3;
    
    public Deadline(String description, String by) {
        super(description);
        this.by = by.substring(startIndexForSubstring);
    }
    
    @Override
    public String toString() {
        return "[" + PREFIX + "]" + super.toString() + " (by: " + by + ")";
    }
    
    @Override
    public String toStorage() {
        return PREFIX + super.toStorage() + PIPE + by;
    }
    
}
