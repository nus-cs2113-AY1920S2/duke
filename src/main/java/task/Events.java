package task;

public class Events extends Task {
    protected String eventTime;
    protected String eventType;

    public Events(String description, String time) {
        super(description);
        this.eventTime = time;
        this.eventType = "[E]";
    }

    @Override
    public String getEventType() {
        return eventType;
    }

    @Override
    public String getTaskTime() {
        return String.format("(%s)", this.eventTime);
    }

    @Override
    public String toString() {
        return String.format("%s%s %s%s", getEventType(),
                super.getStatusIcon(), super.getDescription(), getTaskTime());
    }

    @Override
    public String getDoneResponseMessage(int itemIndexRequested) {
        return String.format("[%d. %s%s %s%s] is marked done!", itemIndexRequested, getEventType(),
                super.getStatusIcon(), super.getDescription(), getTaskTime());
    }

}
