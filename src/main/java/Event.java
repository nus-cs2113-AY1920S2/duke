/**
 * Represents the event objects that are tracked
 * by Duke. A event object corresponds to a occasion that is
 * planned to happen at a specific date and time.
 */
public class Event extends Task {
    protected String timePeriod;

    /**
     * Constructor for the event object.
     * @param description information about the event.
     * @param timePeriod the date and the time period
     * where the event will take place.
     */
    public Event(String description, String timePeriod) {
        super(description);
        this.timePeriod = timePeriod;
    }

    /**
     * Method used to format the event description before printing.
     * @return a string that describes the event object.
     */
    @Override
    public String showFullDescription() {
        return description + " (at: " + timePeriod + ")";
    }

    /**
     * Getter for time period of event object.
     * @return a string that represents the date and the time period
     * of the event.
     */
    public String getTimePeriod() {
        return timePeriod;
    }

    /**
     * Getter for the icon of the event object.
     * @return a string that acts as a icon to differentiate
     * between the various types of tasks.
     */
    @Override
    public String getTypeIcon() {
        return "[E]";
    }
}
