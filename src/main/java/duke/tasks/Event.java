package duke.tasks;

import duke.DukeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDate timePeriod;

    public Event(String description, String t) throws DukeException {
        super(description.trim());
        if(description.isBlank() | t.isBlank())
        {
            throw new DukeException();
        }
        t = t.trim();
        this.timePeriod = LocalDate.parse(t);
    }

    public LocalDate getDate() {
        return this.timePeriod;
    }

    @Override
    public String saveFormat() {
        return "e//" + super.saveFormat() + "//" + timePeriod;
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + timePeriod.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
