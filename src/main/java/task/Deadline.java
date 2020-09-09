package task;

/**
 * Class that represents a deadline task
 */
public class Deadline extends Task {
    protected String deadline;
    protected String eventType;

    /**
     * Constructs a Deadline object
     * @param description description of the Deadline provided by user
     * @param deadline date of the Deadline provided by user
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        this.eventType = "[D]";
    }


    /**
     * Returns String representing the event type
     * @return String representing the event type
     */
    public String getEventType() {
        return this.eventType;
    }

    /**
     * Returns formatted string of date of deadline
     * @return String containing the date
     */
    public String getTaskTime() {
        return this.deadline;
    }

    /**
     * Returns a formatted string object in list format
     * @return String formatting the deadline task
     */
    @Override
    public String toString() {
        return String.format("%s%s %s(by: %s)", getEventType(),
                super.getStatusIcon(), super.getDescription(), getTaskTime());
    }

    /**
     * Returns a formatted string object is list format being marked done
     * @param itemIndexRequested index of item being marked done
     * @return String formatting message of deadline being marked done
     */
    @Override
    public String getDoneResponseMessage(int itemIndexRequested) {
        return String.format("[%d. %s%s %s(by: %s)] is marked done!", itemIndexRequested, getEventType(),
                super.getStatusIcon(), super.getDescription(), getTaskTime());
    }
}

