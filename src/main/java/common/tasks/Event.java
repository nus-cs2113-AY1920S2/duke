package common.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Event subclass of a task.
 */
public class Event extends Task {
    
    public Event(String description, Optional<LocalDate> date, Optional<LocalTime> time) {
        super(description, date, time);
    }
    
    public Event(String description, Optional<LocalDate> date, Optional<LocalTime> time, boolean isDone) {
        super(description, date, time);
        this.isDone = isDone;
    }
    
    @Override
    public String toString() {
        if (this.time.isPresent()) {
            return "[E][" + this.getStatusIcon() + "] " + this.description + "(at: "
                + this.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " " 
                + this.getTime().format(DateTimeFormatter.ofPattern("HH:mm")) + ")";
        } else {
            return "[E][" + this.getStatusIcon() + "] " + this.description + "(at: "
                + this.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ")";
        }
    }
}
