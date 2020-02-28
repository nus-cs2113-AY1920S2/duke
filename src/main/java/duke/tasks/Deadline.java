package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Deadline extends Task {

    public Deadline(String description, LocalDate by) {
        super(description);
        this.date = by;
        this.type = "D";
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", this.type, super.toString(), 
                date.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.UK)));
    }
    
    @Override
    public String getFileString() {
        return String.format("%s | %d | %s | %s", type, isDone ? 1 : 0, description, date);
    }
}
