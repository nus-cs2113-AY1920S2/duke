package duke.tasks;

import duke.parser.Parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

/**
 * Class to represent a deadline task
 */
public class Deadline extends Task {
    public static final Pattern LINE_FORMAT = Pattern.compile("^D,[yn],(\\w\\s*)+,\\d{1,2}/\\d{1,2}/\\d{4}\\s+\\d{1,2}:\\d{2}");
    private LocalDateTime dueDateTime;

    public Deadline(String description, LocalDateTime dueDateTime, boolean isDone) {
        super(description);
        this.dueDateTime = dueDateTime;
        this.isDone = isDone;
    }

    /**
     * Get if the deadline's associated dateTime is before the target dateTime
     * @param dateTime target dateTime
     * @return if the deadline's associated dateTime is before the target dateTime
     */
    public boolean getIsBy(LocalDateTime dateTime) {
        return dueDateTime.isBefore(dateTime);
    }

    /**
     * Get if the deadline's associated dateTime is on the same day as the target date
     * @param date target date
     * @return if the deadline's associated dateTime is on the same day as the target date
     */
    public boolean getIsOn(LocalDate date) {
        return dueDateTime.toLocalDate().equals(date);
    }

    /**
     * get the string representation of this deadline
     * @return string representation of this deadline
     */
    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: " +
                Parser.DTF.format(dueDateTime) + ")";
    }

    /**
     * get the string representation of this deadline formatted for saving to file
     * @return the string representation of this deadline formatted for saving to file
     */
    public String toFormattedString() {
        String done = isDone ? "y" : "n";
        return "D," + done + "," + description + "," + Parser.DTF.format(dueDateTime);
    }
}
