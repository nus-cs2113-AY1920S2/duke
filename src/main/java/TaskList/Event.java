package TaskList;

/**
 * Represents a task with deadline
 */
public class Event extends Task {
    protected String eventDate;
    public Event(String description, String eventDate) {
        super(description);
        this.eventDate = eventDate;
        this.itemType = 'E';
    }

    /**
     * Prints the content of the Event type task
     */
    @Override
    public String printObject() {
        return ("[" + itemType + "][" + getStatusIcon() +"] "+ description + " (at: " + eventDate + ")");
    }

    /**
     * Reformat Event task format for saving into file
     */
    @Override
    public String createStrForSaving() {
        return itemType + " | " + convertBoolean() + " | " + description + " | " + eventDate;
    }
}
