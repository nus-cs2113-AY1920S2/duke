/**
 * Represents event tasks
 * An Event object corresponds to event description, event details and boolean to check if event is completed
 */

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

    /**
     * Returns the event details
     * @return event details
     */
    public String getEventDetails() {
        return eventDetails;
    }

    /**
     * Returns string format for displaying on CLI
     * @return  String representation for Event
     */
    @Override
    public String toString() {
        return "[E][ " + super.getStatusIcon() + " ] " + super.getDescription() + "(at: " + getEventDetails() + ")";
    }

    /**
     * Returns the Event' details , separated by commas
     * @return String format to be stored in text file
     */
    @Override
    public String storeText() {
        return "[E]," +  super.getStatus() + "," + super.getDescription() + "," + getEventDetails();
    }
}