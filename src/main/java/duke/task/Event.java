package duke.task;

public class Event extends Task {

    private String eventDate;

    public Event (String description, String eventDate) {
        super(description);
        this.eventDate = eventDate;

        this.taskType = TaskType.E;
    }

    public String getEventDate () {
        return eventDate;
    }

    public String setEventDate () {
        return eventDate;
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)", taskType, super.toString(), eventDate);
    }
}
