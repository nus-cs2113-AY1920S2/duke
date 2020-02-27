package duke.tasks;

import duke.Main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDateTime startDateTime;

    public Event(String description, String startDateTime) throws DateTimeParseException {
        super(description);
        this.startDateTime = LocalDateTime.parse(startDateTime, Main.DTF);
    }

    public Event(String description, String startDateTime, boolean isDone) throws DateTimeParseException {
        this(description, startDateTime);
        this.isDone = isDone;
    }

    public boolean getIsBy(LocalDateTime dateTime) {
        return startDateTime.isBefore(dateTime);
    }

    public boolean getIsOn(LocalDate date) {
        return startDateTime.toLocalDate().equals(date);
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + " (at: " + startDateTime + ")";
    }

    public String toFormattedString() {
        String done = isDone ? "y" : "n";
        return "E," + done + "," + description + "," + startDateTime;
    }
}
