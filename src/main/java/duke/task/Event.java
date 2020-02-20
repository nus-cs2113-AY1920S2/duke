package duke.task;

public class Event extends Task {
    String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date + ")";
    }
}
