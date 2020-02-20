package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected String at;
    private boolean isStandardTime;
    protected LocalDate date;

    public Event(String description, String time) {
        super(description);
        this.at = time;
        isStandardTime = false;
        try {
            this.date = LocalDate.parse(time);
            isStandardTime = true;
        } catch (DateTimeParseException e){

        } finally {
            super.taskType = "E";
        }
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    @Override
    public String toString() {
        String outputTime = isStandardTime? date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : at;
        return "[E] " + super.toString() + " (at: " + outputTime + ")";
    }
}
