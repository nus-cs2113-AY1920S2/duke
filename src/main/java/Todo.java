public class Todo extends Task {
    protected String eventType;

    public Todo(String description){
        super(description);
        this.eventType = "[T]";
    }

    public String getEventType() {
        return eventType;
    }

    @Override
    public String getDescriptionInListFormat() {
        return String.format("%s%s", getEventType(), super.getDescriptionInListFormat());
    }

    @Override
    public String getDoneResponseMessage(int itemIndexRequested) {
        return String.format("[%d. %s%s %s] is marked done!", itemIndexRequested, getEventType(),
                super.getStatusIcon(), super.getDescription());
    }
}
