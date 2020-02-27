package duke.taskList.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import static duke.common.Constants.DEADLINE;

public class Deadline extends Task {

    protected String by;
    protected LocalDate time;

    public Deadline(String description,String by) {
        super(description);
        try{
            this.time = LocalDate.parse(by);
            this.by = time.format(DateTimeFormatter.ofPattern("MMM d yyyy", Locale.US)) + ", " + time.getDayOfWeek();
        } catch (DateTimeParseException e) {
            this.by = by;
        }
    }

    public LocalDate getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toFile() {
        return "D | " + super.toFile() + " | " + by;
    }

    @Override
    public String type() {
        return DEADLINE;
    }
}
