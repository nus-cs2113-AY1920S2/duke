public class Event extends Task {

    protected String eventDetails;

    public Event(String description, String eventDetails) {
        super(description);
        this.eventDetails = eventDetails;
    }

    public Event(String description, boolean isDone, String eventDetails) {
        super(description, isDone);
        this.eventDetails = eventDetails;
    }

    public String getEventDetails() {
        return eventDetails;
    }

    @Override
    public String toString() {
        return "[E][ " + super.getStatusIcon() + " ] " + super.getDescription() + "(at: " + getEventDetails() + ")";
    }

    @Override
    public String storeText() {
        return "[E]," +  super.getStatus() + "," + super.getDescription() + "," + getEventDetails();
    }
}