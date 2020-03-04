package duke.taskManager;

/**
 * The Event class is a Task with specified String description and String location (at).
 * Event class extends from Task class.
 * @author Lim Yu Xiang
 * @version CS2113 AY19/20 Sem 2 Duke
 */
public class Events extends Task {
    private String at;

    /**
     * Public constructor for Event Task
     * @param description Description of task
     * @param at Location/Date/Time of the task
     */
    public Events(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Getter for the Location/Date/Time of the task
     * @return Location/Date/Time of the task
     */
    public String getEvent(){
        return at;
    }

    /**
     * Return a string representation of the task
     * @return [taskStatus] followed by the description
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + getEvent() + ")" ;
    }

}
