package duke.tasks;

import duke.parser.Parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

/**
 * Class to represent an event task
 */
public class Event extends Task {
    public static final Pattern LINE_FORMAT = Pattern.compile("^E,[yn],(\\w\\s*)+," +
            "\\d{1,2}/\\d{1,2}/\\d{4}\\s+\\d{1,2}:\\d{2}");
    private LocalDateTime startDateTime;

    /**
     * @param description event description
     * @param startDateTime event start dateTime
     * @param isDone whether event is done or not
     */
    public Event(String description, LocalDateTime startDateTime, boolean isDone) {
        super(description);
        this.startDateTime = startDateTime;
        this.isDone = isDone;
    }

    /**
     * Get if the event's associated dateTime is before the target dateTime
     * @param dateTime target dateTime
     * @return if the event's associated dateTime is before the target dateTime
     */
    public boolean getIsBy(LocalDateTime dateTime) {
        return startDateTime.isBefore(dateTime);
    }

    /**
     * Get if the event's associated dateTime is on the same day as the target date
     * @param date target date
     * @return if the event's associated dateTime is on the same day as the target date
     */
    public boolean getIsOn(LocalDate date) {
        return startDateTime.toLocalDate().equals(date);
    }

    /**
     * get the string representation of this event
     * @return string representation of this event
     */
    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + " (at: " + Parser.DTF.format(startDateTime) + ")";
    }

    /**
     * get the string representation of this event formatted for saving to file
     * @return the string representation of this event formatted for saving to file
     */
    public String toFormattedString() {
        String done = isDone ? "y" : "n";
        return "E," + done + "," + description + "," + Parser.DTF.format(startDateTime);
    }
}
