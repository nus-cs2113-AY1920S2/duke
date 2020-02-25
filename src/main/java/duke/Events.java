package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Events extends Task {

    protected LocalDate date;
    protected String time;

    public Events(String description, LocalDate date,String time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + " " +time + ")";
    }

    @Override
    public String showSearch() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))  + " " + time +")";
    }
}

