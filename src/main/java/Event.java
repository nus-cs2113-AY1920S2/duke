public class Event extends Task{
    protected String at;

    public Event(String description, String time) {
        super(description);
        this.at = time;
        super.taskType = "E";
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
