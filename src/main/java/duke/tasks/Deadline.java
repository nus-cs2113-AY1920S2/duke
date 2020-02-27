package duke.tasks;

import duke.Main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime dueDateTime;

    public Deadline(String description, String dueDateTime) throws DateTimeParseException {
        super(description);
        this.dueDateTime = LocalDateTime.parse(dueDateTime, Main.DTF);
    }

    public Deadline(String description, String dueDateTime, boolean isDone) throws DateTimeParseException {
        this(description, dueDateTime);
        this.isDone = isDone;
    }

    public boolean getIsBy(LocalDateTime dateTime) {
        return dueDateTime.isBefore(dateTime);
    }

    public boolean getIsOn(LocalDate date) {
        return dueDateTime.toLocalDate().equals(date);
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: " + dueDateTime + ")";
    }

    public String toFormattedString() {
        String done = isDone ? "y" : "n";
        return "D," + done + "," + description + "," + dueDateTime;
    }
}
