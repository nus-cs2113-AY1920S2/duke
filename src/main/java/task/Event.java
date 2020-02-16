package task;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String convertToData() {
        int isDoneAsInt = isDone ? 1 : 0;
        return String.format("E|" + isDoneAsInt + "|" + this.description + "|" + this.at);
    }
}