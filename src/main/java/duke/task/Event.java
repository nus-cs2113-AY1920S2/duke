package duke.task;

/**
 * Represents an event in the task list, a subclass of Task.
 */
public class Event extends Task {

    protected String at;

    /**
     * Constructor for event class.
     *
     * @param description description of the event to be created
     * @param at the period of the event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String saveTask() {
        return "E | " + (super.isDone ? 1 : 0) + " | " + super.description + " | " + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
