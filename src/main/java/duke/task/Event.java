package duke.task;

/**
 * Represents an type event, an extension of the Task class. An event object is a type
 * of Task that is an event specified by /at for location/time, e.g 'party /at 8pm tonight'
 */
public class Event extends Task {

    protected String at;

    public Event(String description, String at){
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
