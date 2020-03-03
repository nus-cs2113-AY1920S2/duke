package duke.data.task;

/**
 * Represents a Event task.
 */
public class Event extends Task {

    /**
     * Stores additional detail about the event
     */
    protected String at;

    /**
     * Constructor for Event Task Class.
     * <p> <br>
     * It creates a new Event task with the description provided by the user.
     * </p>
     *
     * @param description Contains the description of event.
     * @param at          Contains detail additional of event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the string, description and event of the task in save-able format.<br>
     * Overrides the corresponding method in the parent class.
     *
     * @return output Represents the information related to the task as save-able format.
     */
    @Override
    public String toOutput() {
        String output = "E | " + (isDone ? '1' : '0') + " | " + description
                + " | " + at;
        return output;
    }

    /**
     * Returns the string, description and event of the task.<br>
     * Overrides the corresponding method in the parent class.
     *
     * @return info Represents the information related to the task.
     */
    @Override
    public String toString() {
        String info = "[E]" + super.toString() + " (at: " + at + ")";
        return info;
    }
}
