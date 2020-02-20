package duke.task;

import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String by;
    private boolean isStandardTime;
    protected LocalDate date;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        isStandardTime = false;
        try {
            this.date = LocalDate.parse(by);
            isStandardTime = true;
        } catch (DateTimeParseException e){

        } finally {
            super.taskType = "D";
        }
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String toString() {
        String outputTime = isStandardTime? date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : by;
        return "[D] " + super.toString() + " (by: " + outputTime + ")";
    }
}
