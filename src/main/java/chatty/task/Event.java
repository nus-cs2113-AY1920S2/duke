package chatty.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate startTime;
    private LocalDate endTime;

    public Event(String description, LocalDate startTime, LocalDate endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s to %s)", super.toString(),
                this.startTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                this.endTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }

    @Override
    public String getFileString() {
        return String.format("E|%s|%s|%s to %s", this.isDone, this.description, this.startTime, this.endTime);
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }
}
