public class Event extends Task {
    protected String timePeriod;

    public Event(String description, String timePeriod) {
        super(description);
        this.timePeriod = timePeriod;
    }

    @Override
    public String getDescription() {
        return description + " (at: " + timePeriod + ")";
    }

    @Override
    public String getTypeIcon() {
        return "[E]";
    }
}
