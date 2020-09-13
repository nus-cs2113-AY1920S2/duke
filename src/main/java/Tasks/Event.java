package Tasks;

/**
 * A subclass of <code>Task</code> class.
 *
 * @see Task
 */
public class Event extends Task {
    protected String at;

    /**
     * Class constructor of <code>Event</code> class.
     * @param description Description of the <code>Event</code> task.
     * @param at Date/time of the <code>Event</code> task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns a <code>String</code> of the <code>Event</code> task to be printed.
     * @return <code>String</code> of symbol, done status, description and date/time of <code>Event</code> task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Returns a <code>String</code> of the <code>Event</code> task to be written to the text file.
     * @return <code>String</code> of symbol, done status, description and date/time of <code>Event</code> task.
     */
    @Override
    public String toFileString() {
        return "E," + super.toFileString() + "," + at;
    }
}