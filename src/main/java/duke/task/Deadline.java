package duke.task;

/**
 * Represent the Deadline object
 */
public class Deadline extends Task {
    
    private String by;
    private final String PREFIX = "D";
    private final int startIndexForSubstring = 3;
    
    /**
     * Constructor for Deadline
     *
     * @param description the description of the task
     * @param by          the timeline for the task to be completed
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by.substring(startIndexForSubstring);
    }
    
    /**
     * Return the information as a string
     *
     * @return the string of information relevant to the object
     */
    @Override
    public String toString() {
        return "[" + PREFIX + "]" + super.toString() + " (by: " + by + ")";
    }
    
    /**
     * Return the information with a specific format to the storage
     *
     * @return the string of information relevant to the object
     */
    @Override
    public String toStorage() {
        return PREFIX + super.toStorage() + PIPE + by;
    }
    
}
