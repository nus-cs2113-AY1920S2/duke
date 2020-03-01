package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    public Event(String description, LocalDateTime at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        String dateTimeString = at.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm"));
        return "[E]" + super.toString() + " (at: " + dateTimeString + ")";
    }

    @Override
    public String convertToData() {
        int isDoneAsInt = isDone ? 1 : 0;
        return String.format("E|" + isDoneAsInt + "|" + this.description + "|" + this.at);
    }
}