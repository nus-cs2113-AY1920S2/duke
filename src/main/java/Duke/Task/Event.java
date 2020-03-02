package Duke.Task;
/**
 * Class representing an Event Task.
 */
public class Event extends Task {
    protected String event;
    public Event(String description, String event)  {
        super(description);
        this.event = event;
    }
    public String getEvent(){
        return description;
    }

    @Override
    public String toString() {
        if (event.equals("")) {
            return "[E]" + super.toString();
        }
        return "[E]" + super.toString() + "( at: " + event + " )";
    }
}