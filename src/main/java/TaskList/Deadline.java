package TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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

    @Override
    public String printObject() {
        convertDeadlineFormat(by);
        return ("[" + itemType + "][" + getStatusIcon() + "] "+ description + " (by: " + by + ")");
    }

    @Override
    public String createStrForSaving() {
        return itemType + " | " + convertBoolean() + " | " + description + " | " + by;
    }
}
