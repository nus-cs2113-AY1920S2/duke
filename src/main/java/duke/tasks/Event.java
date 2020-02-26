package duke.tasks;

public class Event extends Task {
    protected String startEndDateTime;

    public Event(String description, String startEndDateTime) {
        super(description);
        this.startEndDateTime = startEndDateTime;
    }

    public Event(String description, String startEndDateTime, boolean isDone) {
        this(description, startEndDateTime);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + " (at: " + startEndDateTime + ")";
    }

    public String toFormattedString() {
        String done = isDone ? "y" : "n";
        return "E," + done + "," + description + "," + startEndDateTime;
    }
}
