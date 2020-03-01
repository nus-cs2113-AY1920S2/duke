package duke.tasks;

public class Event extends Task {

    private String at;

    public Event(String description, String at) {
        super("[E]", description);
        this.at = at;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + at + ")";
    }
}
