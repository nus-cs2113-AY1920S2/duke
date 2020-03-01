package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline class extend from Task class to deal with Deadline
 */
public class Deadline extends Task {
    protected LocalDate time;

    /**
     * Deadline constructor with given description and date
     * @param description
     * @param time date that task needs to be done by
     */
    public Deadline(String description, String time) {
        super(description);
        try {
            this.time = LocalDate.parse(time);
        } catch (DateTimeParseException e) {
            System.out.println("\tPlease input the correct date format");
        }
    }

    /**
     * Override of getTime method in Task
     *
     * @return time of the deadline
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
     * Override of getType method
     *
     * @return first letter of deadline "D"
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * Override of string method in Task
     *
     * @return format of a Deadline's task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getTimeFormatted() + ")";
    }
}
