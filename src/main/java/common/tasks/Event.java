package common.tasks;

public class Event extends Task {
    private final String date;
    
    public Event(String description, String date) {
        super(description);
        this.date = date;
    }
    
    public Event(String description, String date, boolean isDone) {
        super(description);
        this.date = date;
        this.isDone = isDone;
    }
    
    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + this.description
            + "(at: " + this.date + ")";
    }
}
