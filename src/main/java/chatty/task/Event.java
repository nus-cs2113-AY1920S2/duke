package chatty.task;

public class Event extends Task {

    protected String eventPeriod;

    public Event(String description, String eventPeriod) {
        super(description);
        this.eventPeriod = eventPeriod;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.eventPeriod + ")";
    }
}
