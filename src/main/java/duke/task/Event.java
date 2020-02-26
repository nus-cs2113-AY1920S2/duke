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

    /**
     * Returns the string, description and event of the task in save-able format.<br>
     * Overrides the corresponding method in the parent class.
     *
     * @return save Represents the information related to the task as save-able format.
     */
    @Override
    public String saveTask() {
        String save = "E | " + (super.isDone ? 1 : 0) + " | " + super.description + " | " + at;
        return save;
    }

    /**
     * Returns the string, description and event of the task.<br>
     * Overrides the corresponding method in the parent class.
     *
     * @return s Represents the information related to the task.
     */
    @Override
    public String toString() {
        String s = "[E]" + super.toString() + " (at: " + at + ")";
        return s;
    }
}
