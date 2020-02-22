package Features;

import java.time.LocalDate;

public class Event extends Task{
    public Event(String description, String userDate) {
        super(description);
        this.dateToCompleteString = userDate;
    }
    public Event(String description, LocalDate userDate, String userTime) {
        super(description);
        this.dateToCompleteLocalDate = userDate;
        this.timeToComplete = userTime;
    }

    public String getType() {
        return "Event";
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getTimeToComplete() + ")";
    }
}
