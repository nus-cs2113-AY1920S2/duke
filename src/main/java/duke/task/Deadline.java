package duke.task;

public class Deadline extends Task {
    
    private String by;
    private final String PREFIX = "D";
    
    public Deadline(String description, String by) {
        super(description);
        this.by = by.replace("by", "").trim();
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
