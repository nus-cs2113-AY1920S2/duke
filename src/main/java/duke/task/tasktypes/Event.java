package duke.task.tasktypes;

/**
 * A class representing an event task
 */
public class Event extends Task {

    /** Event details such as place, time, etc. */
    private String eventDate;

    public Event (String description, String eventDate) {
        super(description);
        this.eventDate = eventDate;

        this.taskType = TaskType.E;
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)", taskType, super.toString(), eventDate);
    }
}
