public class Event extends Task {
    protected String startEndDateTime;

    public Event(String description, String startEndDateTime) {
        super(description);
        this.startEndDateTime = startEndDateTime;
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + " (at: " + startEndDateTime + ")";
    }
}
