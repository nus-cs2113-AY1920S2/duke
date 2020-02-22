package chatty.task;

/**
 * Class for event task.
 */
public class Event extends Task {

    private String eventPeriod;

    /**
     * Constructor for event task.
     * @param description Description of the event task.
     * @param eventPeriod Event period of the event task.
     */
    public Event(String description, String eventPeriod) {
        super(description);
        this.eventPeriod = eventPeriod;
    }

    /**
     * Converts the event task to string.
     * @return String representing the event task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.eventPeriod);
    }

    /**
     * Converts the event task to a string to be stored in file.
     * @return String representing the event task which should be stored in file.
     */
    @Override
    public String getFileString() {
        return String.format("E|%s|%s|%s", this.getDoneStatus(), this.getDescription(), this.eventPeriod);
    }
}
