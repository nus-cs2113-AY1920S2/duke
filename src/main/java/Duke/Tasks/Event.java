package Duke.Tasks;

/**
 * A class of event task type with description of event and date that it is happening.
 */
public class Event extends Task {

    /**
     * Constructor for Event class.
     *
     * @param action the task description.
     * @param at the date of the event.
     */
    public Event(String action, String at) {
        super(action);
        this.taskType = "E";
        this.date = at;
    }

    /**
     * Creates a String with the Event task.
     *
     * @return String indicating task type, task description, and event date.
     */
    @Override
    public String toString() {
        return "[" + taskType + "]" + super.toString() + "(at: " + date + ")";
    }
}
