package Task;

/**
 * Class that represents ToDo task
 */
public class Todo extends Task {
    protected String eventType;

    /**
     * Constructs a ToDo object
     * @param description description of the ToDo provided by user
     */
    public Todo(String description){
        super(description);
        this.eventType = "[T]";
    }

    /**
     * Returns String representing the event type
     * @return String representing the event type
     */
    public String getEventType() {
        return eventType;
    }

    @Override
    public String getTaskTime() {
        return null;
    }

    /**
     * Returns a formatted string object in list format
     * @return String formatting the Event task
     */
    @Override
    public String toString() {
        return String.format("%s%s %s", getEventType(), super.getStatusIcon(), super.getDescription());
    }

    /**
     * Returns a formatted string object is list format being marked done
     * @param itemIndexRequested index of item being marked done
     * @return String formatting message of ToDo being marked done
     */
    @Override
    public String getDoneResponseMessage(int itemIndexRequested) {
        return String.format("[%d. %s%s %s] is marked done!", itemIndexRequested, getEventType(),
                super.getStatusIcon(), super.getDescription());
    }
}
