package duke.tasks;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.type = "E";
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)", this.type, super.toString(), at);
    }
    
    @Override
    public String getFileString() {
        return String.format("%s | %d | %s | %s", type, isDone ? 1 : 0, description, at);
    }
}
