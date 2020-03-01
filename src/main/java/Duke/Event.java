package Duke;

/**
 * Represents an event-based task happening at a specific date.
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructor for a new event.
     * @param description description for the event
     * @param at date of the event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructor for an event that is read from a storage file.
     * @param description description of the event
     * @param at date of the event
     * @param status status of the event
     */
    public Event(String description, String at, String status) {
        super(description);
        this.at = at;
        if (status.equals("1")) {
            this.markAsDone();
        }
    }

    /**
     * @return a string containing the status, description and date of the event for printing
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }

    /**
     * @return a string of information of the event in the format for saving to a file
     */
    @Override
    public String toSaveFormat() {
        return(super.toSaveFormat() + "E|" + this.at);
    }
}
