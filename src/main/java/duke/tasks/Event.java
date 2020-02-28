package duke.tasks;

import duke.Main;
import duke.exceptions.BadLineFormatException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Class to represent an event task
 */
public class Event extends Task {
    private LocalDateTime startDateTime;

    public Event(String description, String startDateTime) throws BadLineFormatException {
        super(description);
        try {
            this.startDateTime = LocalDateTime.parse(startDateTime, Main.DTF);
        } catch (DateTimeParseException e) {
            throw new BadLineFormatException(e.getMessage());
        }
    }

    public Event(String description, String startDateTime, boolean isDone) throws BadLineFormatException {
        this(description, startDateTime);
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
        return "[E][" + getStatusIcon() + "] " + description + " (at: " + startDateTime + ")";
    }

    /**
     * get the string representation of this event formatted for saving to file
     * @return the string representation of this event formatted for saving to file
     */
    public String toFormattedString() {
        String done = isDone ? "y" : "n";
        return "E," + done + "," + description + "," + Main.DTF.format(startDateTime);
    }
}
