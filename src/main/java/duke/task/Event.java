package duke.task;

/**
 * Type of Task that takes place at a specified date.
 */
public class Event extends Task {
    /** Icon used to represent an Event */
    public static final char EVENT_ICON = 'E';

    /** Date at which the Event will be held */
    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String encodeTask() {
        return String.format("%s|%s|%s|%s", EVENT_ICON, isDone, description, time);
    }

    /**
     * Returns the object representation of an encoded Event.
     * @param encodedTask a string returned by method Task.encodeTask()
     * @return an Event object whose information was stored in encodedTask
     */
    public static Event decodeTask(String encodedTask) {
        String[] tokens = encodedTask.split("\\" + DELIMITER);
        boolean isDone = Boolean.parseBoolean(tokens[1]);
        String description = tokens[2];
        String time = tokens[3];
        Event event = new Event(description, time);
        if (isDone) {
            try {
                event.markAsDone();
            } catch (DukeException de) {
                // user feedback not required
            }
        }
        return event;
    }

    @Override
    public String toString() {
        return "[" + EVENT_ICON + "]" + super.toString() + " (at: " + time + ")";
    }
}
