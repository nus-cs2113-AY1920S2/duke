package duke.tasks;

import duke.Main;
import duke.exceptions.BadLineFormatException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Class to represent a deadline task
 */
public class Deadline extends Task {
    private LocalDateTime dueDateTime;

    public Deadline(String description, String dueDateTime) throws BadLineFormatException {
        super(description);
        try {
            this.dueDateTime = LocalDateTime.parse(dueDateTime, Main.DTF);
        } catch (DateTimeParseException e) {
            throw new BadLineFormatException(e.getMessage());
        }
    }

    public Deadline(String description, String dueDateTime, boolean isDone) throws BadLineFormatException {
        this(description, dueDateTime);
        this.isDone = isDone;
    }

    public boolean getIsBy(LocalDateTime dateTime) {
        return dueDateTime.isBefore(dateTime);
    }

    public boolean getIsOn(LocalDate date) {
        return dueDateTime.toLocalDate().equals(date);
    }

    /**
     * get the string representation of this deadline
     * @return string representation of this deadline
     */
    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: " + dueDateTime + ")";
    }

    /**
     * get the string representation of this deadline formatted for saving to file
     * @return the string representation of this deadline formatted for saving to file
     */
    public String toFormattedString() {
        String done = isDone ? "y" : "n";
        return "D," + done + "," + description + "," + Main.DTF.format(dueDateTime);
    }
}
