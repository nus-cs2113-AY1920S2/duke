package duke;

public class Event extends Task {
    private final String date;
    
    public Event(String description, String date) {
        super(description);
        this.date = date;
    }
    
    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + this.description
            + "(at: " + this.date + ")";
    }
}
