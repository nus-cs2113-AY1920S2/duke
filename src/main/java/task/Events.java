package task;

/**
 * Class that represents an Event
 */
public class Events extends Task {
    protected String eventTime;
    protected String eventType;

    /**
     * Constructs an Event object
     * @param description description of Event provided by user
     * @param time time period of Event provided by user
     */
    public Events(String description, String time) {
        super(description);
        this.eventTime = time;
        this.eventType = "[E]";
    }

    /**
     * Returns formatted string of date of Event
     * @return String containing the date
     */
    public String getTaskTime() {
        return String.format("by: %s", this.eventTime);
    }

    /**
     * Returns String representing the event type
     * @return String representing the event type
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * Returns a formatted string object in list format
     * @return String formatting the Event task
     */
    @Override
    public String toString() {
        return String.format("%s%s %s(%s)", getEventType(),
                super.getStatusIcon(), super.getDescription(), getTaskTime());
    }

    /**
     * Returns a formatted string object is list format being marked done
     * @param itemIndexRequested index of item being marked done
     * @return String formatting message of Event being marked done
     */
    @Override
    public String getDoneResponseMessage(int itemIndexRequested) {
        return String.format("[%d. %s%s %s(%s)] is marked done!", itemIndexRequested, getEventType(),
                super.getStatusIcon(), super.getDescription(), getTaskTime());
    }
}
