package duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

/**
 * Class to represent a todo task
 */
public class ToDo extends Task {
    public static final Pattern LINE_FORMAT = Pattern.compile("^T,[yn],(\\w\\s*)+");
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        this(description);
        this.isDone = isDone;
    }

    /**
     * Get if the ToDo is on a specified date. ToDo doesn't have a date so always return false
     * @param date the target date
     * @return false
     */
    public boolean getIsOn(LocalDate date) {
        return false;
    }

    /**
     * Get if the ToDo is due by/on a specified date. ToDo doesn't have a date so always return false
     * @param dateTime the target date
     * @return false
     */
    public boolean getIsBy(LocalDateTime dateTime) {
        return false;
    }

    /**
     * get the string representation of this todo
     * @return string representation of this todo
     */
    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }

    /**
     * get the string representation of this todo formatted for saving to file
     * @return the string representation of this todo formatted for saving to file
     */
    public String toFormattedString() {
        String done = isDone ? "y" : "n";
        return "T," + done + "," + description;
    }
}
