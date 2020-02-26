package duke.task;

/**
 * Class representing Event task.
 */
public class Event extends Task {

    protected String at;

    /**
     * Default constructor for event class.
     * @param description String containing description of event task
     * @param at String containing date and time when event will occur
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Formats event task into a string.
     * @return String containing an indication of Event task, description of event task and date/time of event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }




}
