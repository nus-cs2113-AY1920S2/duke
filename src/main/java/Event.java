public class Event extends Task {

    protected String at;

    public Event(String description, int taskNumber, String at) {
        super(description, taskNumber);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
