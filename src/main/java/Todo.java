/**
 * Represents the things to do objects that are tracked
 * by Duke. This object corresponds to an activity that
 * needs to be done that does not have a specific due date.
 */
public class Todo extends Task {

    /**
     * Constructor for the todo object.
     * @param description information about the activity to be done
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Getter for the icon of the todo object.
     * @return a string that acts as a icon to differentiate
     * between the various types of tasks
     */
    @Override
    public String getTypeIcon() {
        return "[T]";
    }
}
