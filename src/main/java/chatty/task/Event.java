package chatty.task;

public class Event extends Task {

    private String eventPeriod;

    public Event(String description, String eventPeriod) {
        super(description);
        this.eventPeriod = eventPeriod;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.eventPeriod);
    }

    @Override
    public String getFileString() {
        return String.format("E|%s|%s|%s", this.isDone, this.description, this.eventPeriod);
    }
}
