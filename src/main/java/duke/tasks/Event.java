package duke.tasks;

/**
 * Class to represent an event task
 */
public class Event extends Task {
    protected String startEndDateTime;

    public Event(String description, String startEndDateTime) {
        super(description);
        this.startEndDateTime = startEndDateTime;
    }

    public Event(String description, String startEndDateTime, boolean isDone) {
        this(description, startEndDateTime);
        this.isDone = isDone;
    }

    /**
     * get the string representation of this event
     * @return string representation of this event
     */
    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + " (at: " + startEndDateTime + ")";
    }

    /**
     * get the string representation of this event formatted for saving to file
     * @return the string representation of this event formatted for saving to file
     */
    public String toFormattedString() {
        String done = isDone ? "y" : "n";
        return "E," + done + "," + description + "," + startEndDateTime;
    }
}
