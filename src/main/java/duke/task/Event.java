package duke.task;

import duke.Parser;

import java.time.LocalDateTime;

/**
 * Type of Task that takes place at a specified date.
 */
public class Event extends Task {
    /** Icon used to represent an Event */
    public static final char EVENT_ICON = 'E';

    /** Starting and ending time of the Event */
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String encodeTask() {
        return String.format("%s|%s|%s|%s|%s",
                EVENT_ICON, isDone, description,
                startTime.format(Parser.INPUT_DATE_FORMAT),
                endTime.format(Parser.INPUT_DATE_FORMAT));
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
        LocalDateTime startTime = Parser.parseDate(tokens[3]);
        LocalDateTime endTime = Parser.parseDate(tokens[4]);
        Event event = new Event(description, startTime, endTime);
        if (isDone) {
            event.markAsDone();
        }
        return event;
    }

    @Override
    public String toString() {
        return "[" + EVENT_ICON + "]"
                + super.toString()
                + " (at: "
                + startTime.format(Parser.PRINT_DATE_FORMAT)
                + " - "
                + endTime.format(Parser.PRINT_TIME_FORMAT)
                + ")";
    }
}
