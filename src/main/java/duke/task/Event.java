package duke.task;

public class Event extends Task {
    private String eventTime; // string containing deadline

    public String getEventTime() {
        return eventTime;
    }

    /**
     * Returns the type of the task.
     *
     * @return The type of the task.
     */
    @Override
    public String getType() {
        return "E";
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    public Event(boolean isDone, String description, String eventTime) {
        super(isDone, description);
        this.eventTime = eventTime;
    }

    @Override
    public String toData(int taskId) {
        String dataLine = taskId + "," + this.getType() + "," + this.isDone() + "," + this.getDescription() + "," + this.getEventTime();
        return dataLine;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.eventTime + ")";
    }
}
