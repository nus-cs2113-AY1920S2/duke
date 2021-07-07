package task;

/**
 * A sub-class of the Task class for events.
 * Contains location of the event.
 *
 * @see Task
 */

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    /**
     * Returns a string representation of event task to be output to
     * the bot's UI.
     *
     * @return string of symbol, done status, description and
     *         location of event task.
     * */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getAt() + ")";
    }

    /**
     * Returns a string representation of event task to be written to
     * the storage file.
     *
     * @return string of symbol, done status, description and
     *         location of event task.
     * */
    @Override
    public String toFileString() {
        return "E," + super.toFileString() + "," + this.getAt();
    }
}