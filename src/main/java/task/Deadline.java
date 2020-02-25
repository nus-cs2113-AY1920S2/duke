package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.taskType = "D";
    }

    @Override
    public String getFileRecord() {
        int doneValue = isDone ? 1 : 0;
        return String.format("%s,%d,%s,%s\n", taskType, doneValue, description.strip(), by);
    }

    @Override
    public String toString() {
        LocalDate date = null;
        try {
            date = LocalDate.parse(by); // Parse input of form yyyy-mm-dd
        } catch (DateTimeParseException e){
            return "[D]" + super.toString() + " (by: " + by + ")"; // If not in correct date format, print original String
        }
        String parsedDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + parsedDate + ")";
    }
}