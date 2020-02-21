package duke.task;

/**
 * Represents an event.
 */
public class Event extends Task {

    /** Duration of the event */
    protected String duration;

    public Event(String description,String duration) {
        super(description);
        this.duration = duration;
    }

    /**
     * Obtains the duration of the event.
     *
     * @return Duration of the event.
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Defines the string format of <code>Event</code> object.
     *
     * @return String format of <code>Event</code> object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + duration + ")";
    }
}
