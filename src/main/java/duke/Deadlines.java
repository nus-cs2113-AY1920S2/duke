package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Task item class of the Duke project
 * Extends Task class contains fields: name by
 */
public class Deadlines extends Task {

    protected String by;

    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

}