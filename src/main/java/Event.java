package src.main.java;

public class Event extends Task {

    private final String EVENT = "E";

    protected String date;

    public Event(String description, String date) {
        super(description);
        this.taskType = EVENT;
        this.date = date;
    }

    public String toString() {
        return "[" + taskType + "]" + super.toString() + " (at: " + date + ")";
    }
}
