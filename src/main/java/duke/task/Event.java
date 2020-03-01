package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDate time;

    public Event(String description, String time) {
        super(description);
        try {
            this.time = LocalDate.parse(time);
        } catch (DateTimeParseException e) {
            System.out.println("\tPlease input the correct date format");
        }
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public LocalDate getTime() {
        return time;
    }

    @Override
    public String getTimeFormatted() {
        return this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getTimeFormatted() + ")";
    }
}
