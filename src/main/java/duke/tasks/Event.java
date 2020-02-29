package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event extends Task {
    public Event(String description, LocalDate at) {
        super(description);
        this.date = at;
        this.type = "E";
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)", this.type, super.toString(), 
                date.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.UK)));
    }
    
    @Override
    public String getFileString() {
        return String.format("%s | %d | %s | %s", type, isDone ? 1 : 0, description, date);
    }
}
