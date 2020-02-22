package Features;

/**
 * Extension of <code>Task</code> class specifying an <code>Event</code> task.
 */
public class Event extends Task{
    public Event(String description, String at) {
        super(description);
        this.timeToComplete = at;
    }

    public String getType() {
        return "Event";
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.timeToComplete + ")";
    }
}
