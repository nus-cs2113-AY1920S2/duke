package duke.task;

/**
 * Class representing an Event Task.
 */

public class Event extends Task {

    protected String event;

    public Event(String description, String event) {
        super(description);
        this.event = event;
    }

    /**
     * Retrieves the Event Description.
     * @return description
     */
    public String getEvent() {
        return description;
    }

    @Override
    public String toString() {
        if (event.equals("")) {
            return "[E]" + super.toString();
        }
        return "[E]" + super.toString() + "( at: " + event + " )";
    }
}