package duke.tasks;

/**
 * Event inherits from Task and is the public class responsible for storing information about the task.
 */

public class Event extends Task {

    /**
     * The location of the event entered by the user.
     */

    private String at;

    /**
     * Constructs the Event object.
     * @param description the description of the event entered by the user.
     * @param at the location of the event entered by the user.
     */

    public Event(String description, String at) {
        super("[E]", description);
        this.at = at;
    }

    /**
     * Returns the information related to the Event object.
     * @return the information related to the Event object.
     */

    @Override
    public String toString() {
        return super.toString() + " (at: " + at + ")";
    }
}
