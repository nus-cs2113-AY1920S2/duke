package duke.tasks;

import duke.DukeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) throws DukeException {
        super(description.trim());
        if(description.isBlank() | by.isBlank())
        {
            throw new DukeException();
        }
        by = by.trim();
        this.by = LocalDate.parse(by);
    }

    public LocalDate getDate() {
        return this.by;
    }


    @Override
    public String saveFormat() {
        return "d//" + super.saveFormat() + "//" + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
