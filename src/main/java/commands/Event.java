package commands;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.taskDescription = "event";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String getAtDescription() {
        return (" (at: " + at + ")");
    }
}
