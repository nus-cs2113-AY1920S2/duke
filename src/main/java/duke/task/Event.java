package duke.task;

/**
 * a type of task that has an additional venue/time/date to it,
 * comes in the form of the 'at'
 */
public class Event extends Task {

    protected String at;

    /**
     * Returns the task created with the specified
     * description.
     *
     * @param description the description of the task
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }


    @Override
    public String getExtra() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
