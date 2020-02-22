package TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.io.FileNotFoundException;

/**
 * Represents a task with deadline
 */
public class Deadline extends Task {
    protected String by;
    private LocalDate deadline;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.itemType = 'D';
    }

    public void convertDeadlineFormat(String oldFormat) {
        try {
            deadline = LocalDate.parse(oldFormat);
            by = deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            by = oldFormat;
        }
    }

    /**
     * Prints the content of the Deadline type task
     */
    @Override
    public String printObject() {
        convertDeadlineFormat(by);
        return ("[" + itemType + "][" + getStatusIcon() + "] "+ description + " (by: " + by + ")");
    }

    /**
     * Reformat Deadline task format for saving into file
     */
    @Override
    public String createStrForSaving() {
        return itemType + " | " + convertBoolean() + " | " + description + " | " + by;
    }
}
