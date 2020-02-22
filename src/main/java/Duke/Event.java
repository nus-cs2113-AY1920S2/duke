package Duke;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, String at, String status) {
        super(description);
        this.at = at;
        if (status.equals("1")) {
            this.markAsDone();
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }

    @Override
    public String toSaveFormat() {
        return(super.toSaveFormat() + "E|" + this.at);
    }
}
