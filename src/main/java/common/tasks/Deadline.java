package common.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Deadline extends Task {
    
    public Deadline(String description, Optional<LocalDate> date, Optional<LocalTime> time) {
        super(description, date, time);
    }
    
    public Deadline(String description, Optional<LocalDate> date, Optional<LocalTime> time, boolean isDone) {
        super(description, date, time);
        this.isDone = isDone;
    }
    
	@Override
	public String toString() {
		if (this.time.isPresent()) {
			return "[D][" + this.getStatusIcon() + "] " + this.description + "(by: "
					+ this.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " " 
					+ this.getTime().format(DateTimeFormatter.ofPattern("HH:mm")) + ")";
		} else {
			return "[D][" + this.getStatusIcon() + "] " + this.description + "(by: "
					+ this.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ")";
		}
	}
}
