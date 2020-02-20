package duke.task;

/**
 * Represents an event task.
 */
public class Event extends Task {
    protected String at;

    public Event(String description, String time) {
        super(description);
        this.at = time;
        super.taskType = "E";
    }

    /**
     * Gets this event's time
     * @return the event's time
     */
    public String getAt() {
        return at;
    }

    /**
     * Sets this event's time
     */
    public void setAt(String at) {
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + at + ")";
    }
}
