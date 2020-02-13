package chatty.task;

import static chatty.util.Constants.FILE_FIELD_SEPARATOR_FOR_WRITE;

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

    @Override
    public String getFileString() {
        return "E" + FILE_FIELD_SEPARATOR_FOR_WRITE + this.isDone + FILE_FIELD_SEPARATOR_FOR_WRITE + this.description +
                FILE_FIELD_SEPARATOR_FOR_WRITE + this.eventPeriod;
    }
}
