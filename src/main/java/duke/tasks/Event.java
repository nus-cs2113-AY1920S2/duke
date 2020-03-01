package duke.tasks;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super("[E]", description);
        this.at = at;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + at + ")";
    }
}
