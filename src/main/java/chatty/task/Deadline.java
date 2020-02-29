package chatty.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate dateTime;

    public Deadline(String description, LocalDate dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }

    @Override
    public String getFileString() {
        return String.format("D|%s|%s|%s", this.isDone, this.description, this.dateTime);
    }

    public LocalDate getDateTime() {
        return dateTime;
    }
}
