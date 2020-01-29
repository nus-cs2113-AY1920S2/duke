public class Events extends Task {
    protected String eventTime;
    protected String eventType;

    public Events(String description, String time){
        super(description);
        this.eventTime = time;
        this.eventType = "[E]";
    }

    public String getEventTime() {
        return String.format("(%s)", this.eventTime);
    }

    public String getEventType() {
        return eventType;
    }

    @Override
    public String getDescriptionInListFormat() {
        return String.format("%s%s%s", getEventType(),
                super.getDescriptionInListFormat(), getEventTime());
    }

    @Override
    public String getDoneResponseMessage(int itemIndexRequested) {
        return String.format("[%d. %s%s %s%s] is marked done!", itemIndexRequested, getEventType(),
                super.getStatusIcon(), super.getDescription(), getEventTime());
    }

}
