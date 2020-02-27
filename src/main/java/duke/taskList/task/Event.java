package duke.taskList.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import static duke.common.Constants.EVENT;

public class Event extends Task {

    protected String at;
    protected LocalDate time;

    public Event(String description,String at) {
        super(description);
        try{
            this.time = LocalDate.parse(at);
            this.at = time.format(DateTimeFormatter.ofPattern("MMM d yyyy", Locale.US)) + ", " + time.getDayOfWeek();
        } catch (DateTimeParseException e) {
            this.at = at;
        }
    }

    public LocalDate getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String toFile() {
        return "E | " + super.toFile() + " | " + at;
    }

    @Override
    public String type() {
        return EVENT;
    }
}
