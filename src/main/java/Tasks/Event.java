package Tasks;

/**
 * Represents an Tasks.Event object for tasks
 * An Event object corresponds to a description, location/time, and boolean isDone to check if Tasks.Event task has been
 * completed
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
     * Returns the location/time of Event
     * @return eventDetails location/time of Event
     */
    public String getEventDetails() {
        return eventDetails;
    }

    /**
     * Returns String format for Event tasks to be printed on CLI
     * @return String format of Event tasks
     */
    @Override
    public String toString() {
        return "[E][ " + super.getStatusIcon() + " ] " + super.getDescription() + "(at: " + getEventDetails() + ")";
    }

    /**
     * Returns String format for Event tasks to be stored in text file
     * @return String format of Event tasks
     */
    @Override
    public String storeText() {
        return "[E]," +  super.getStatus() + "," + super.getDescription() + "," + getEventDetails();
    }
}