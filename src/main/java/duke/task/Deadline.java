package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    String dateTime;
    LocalDate date;
    LocalTime time;

    public Deadline(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
        date = LocalDate.parse(dateTime.split(" ")[0]);
        time = LocalTime.parse(dateTime.split(" ")[1]);
    }

    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String getTime() {
        return this.time.toString();
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDate() + ", " + getTime() + ")";
    }
}
