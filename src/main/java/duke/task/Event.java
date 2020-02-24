package duke.task;

public class Event extends Task {
    
    private String at;
    private final String PREFIX = "E";
    private final int startIndexForSubstring = 3;
    
    public Event(String description, String at) {
        super(description);
        this.at = at.substring(startIndexForSubstring);
    }
    
    @Override
    public String toString() {
        return "[" + PREFIX + "]" + super.toString() + " (at: " + at + ")";
    }
    
    @Override
    public String toStorage() {
        return PREFIX + super.toStorage() + PIPE + at;
    }
    
}
