package duke.task;

import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description.trim());
        by = by.trim();
        this.by = LocalDate.parse(by);
    }

    public LocalDate getDate() {
        return this.by;
    }

    @Override
    public String formatResult() {
        return "d|" + super.formatResult() + "|" + by;
    }

    public String toString() {
        return "[D]" + super.toString() + "(by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

