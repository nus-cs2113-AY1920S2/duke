package Task;

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
    public String getTaskTime() {
        return null;
    }

    @Override
    public String toString() {
        return String.format("%s%s %s", getEventType(), super.getStatusIcon(), super.getDescription());
    }

    @Override
    public String getDoneResponseMessage(int itemIndexRequested) {
        return String.format("[%d. %s%s %s] is marked done!", itemIndexRequested, getEventType(),
                super.getStatusIcon(), super.getDescription());
    }
}
