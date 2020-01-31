public class Event extends Task {

    String timePeriod;

    public Event(String description, String t) {
        super(description);
        this.timePeriod = t;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "at: " + this.timePeriod;
    }
}
