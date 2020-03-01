package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event class extend from Task class to deal with Event
 */
public class Event extends Task {
    protected LocalDate time;

    /**
     * Event constructor with given description and date
     * @param description
     * @param time date that event happens
     */
    public Event(String description, String time) {
        super(description);
        try {
            this.time = LocalDate.parse(time);
        } catch (DateTimeParseException e) {
            System.out.println("\tPlease input the correct date format");
        }
    }

    /**
     * Override of getType method
     *
     * @return first letter of event "E"
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Override of getTime method in Task
     *
     * @return time of the event
     */
    @Override
    public LocalDate getTime() {
        return time;
    }

    /**
     * Override of the time's format method
     *
     * @return time format in human readable way
     */
    @Override
    public String getTimeFormatted() {
        return this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Override of string method in Task
     *
     * @return format of a Event's task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getTimeFormatted() + ")";
    }
}
