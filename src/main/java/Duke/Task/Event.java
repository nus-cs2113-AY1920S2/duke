package Duke.Task;

/**
 * Class representing an Event Task.
 */
public class Event extends Task {
    protected String by;
    public Event(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getEvent(){
        return description;
    }

    @Override
    public String toString() {
        if (by.equals("")) {
            return "[E]" + super.toString();
        }
        return "[E]" + super.toString() + "( at: " + by + ")";
    }
}