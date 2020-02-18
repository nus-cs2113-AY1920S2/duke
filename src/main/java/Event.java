/**
 * Represents an Event object for tasks
 * An Event object corresponds to a description, time/date, and boolean isDone to check if Event task has been
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
     * Returns the date/time of Event
     * @return eventDetails
     */
    public String getEventDetails() {
        return eventDetails;
    }

    /**
     * Returns String format for Deadline tasks to be printed on CLI
     * @return String format of Deadline tasks
     */
    @Override
    public String toString() {
        return "[E][ " + super.getStatusIcon() + " ] " + super.getDescription() + "(at: " + getEventDetails() + ")";
    }

    /**
     * Returns String format for Deadline tasks to be stored in text file
     * @return String format of Deadline tasks
     */
    @Override
    public String storeText() {
        return "[E]," +  super.getStatus() + "," + super.getDescription() + "," + getEventDetails();
    }
}