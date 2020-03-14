package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Task item class of the Duke project
 * Extends Task class contains fields: name by
 */
public class Events extends Task {

    protected String date;
    protected String time;

    public Events(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + " "  + ")";
    }

}

