public class Event extends Task {
    protected String timePeriod;

    public Event(String description, String timePeriod) {
        super(description);
        this.timePeriod = timePeriod;
    }

    @Override
    public String showFullDescription() {
        return description + " (at: " + timePeriod + ")";
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    @Override
    public String getTypeIcon() {
        return "[E]";
    }
}
