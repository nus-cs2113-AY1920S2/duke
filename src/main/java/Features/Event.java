package Features;

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
