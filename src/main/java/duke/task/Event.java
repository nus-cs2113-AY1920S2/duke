package duke.task;

public class Event extends Task {

    private String eventDate;

    public Event (String description, String eventDate) {
        super(description);
        this.eventDate = eventDate;
    }

    public String getEventDate () {
        return eventDate;
    }

    public String setEventDate () {
        return eventDate;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), eventDate);
    }
}
