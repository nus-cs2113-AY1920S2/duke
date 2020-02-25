package duke.task;
/**
 * Represent the Todo object
 */
public class Todo extends Task {
    
    private final String PREFIX = "T";
    
    /**
     * Constructor for Todo
     *
     * @param description the description of the task
     */
    public Todo(String description) {
        super(description);
    }
    
    /**
     * Return the information as a string
     *
     * @return the string of information relevant to the object
     */
    @Override
    public String toString() {
        return "[" + PREFIX + "]" + super.toString();
    }
    
    /**
     * Return the information with a specific format to the storage
     *
     * @return the string of information relevant to the object
     */
    @Override
    public String toStorage() {
        return PREFIX + super.toStorage();
    }
    
}
