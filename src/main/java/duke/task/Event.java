package duke.task;

/**
 * Represent the Event object
 */
public class Event extends Task {
    
    private String at;
    private final String PREFIX = "E";
    private final int startIndexForSubstring = 3;
    
    /**
     * Constructor for Event
     *
     * @param description the description of the task
     * @param at          the timeline for the task to be completed
     */
    public Event(String description, String at) {
        super(description);
        this.at = at.substring(startIndexForSubstring);
    }
    
    /**
     * Return the information as a string
     *
     * @return the string of information relevant to the object
     */
    @Override
    public String toString() {
        return "[" + PREFIX + "]" + super.toString() + " (at: " + at + ")";
    }
    
    /**
     * Return the information with a specific format to the storage
     *
     * @return the string of information relevant to the object
     */
    @Override
    public String toStorage() {
        return PREFIX + super.toStorage() + PIPE + at;
    }
    
}
