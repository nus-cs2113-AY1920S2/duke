package duke.tasks;

/**
 * Class for Event task.
 */
public class Event extends Task {

    protected String at;

    /**
     * Constructor for Deadline class.
     * @param description String for description of event task.
     * @param at String for date of event task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.taskDescription = "event";
    }

    /**
     * To display event task message to user.
     * @return String consisting of event task, description and date.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}