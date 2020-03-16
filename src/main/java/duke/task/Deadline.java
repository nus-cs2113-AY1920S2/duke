package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class is a Task with specified String description and String dateTime.
 * The class stores the date as a LocalDate Object, time as a LocalTime Object and their combined
 * String representation as a String dateTime.
 * Deadline class extends from Task class.
 * @author Lam Yue Wei
 * @version CS2113 AY19/20 Sem 2 Duke
 */
public class Deadline extends Task {
    String dateTime;
    LocalDate date;
    LocalTime time;

    /**
     * Public constructor for Deadline.
     * @param description Description of the Deadline Task.
     * @param dateTime Due date and time of the Deadline Task.
     */
    public Deadline(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
        date = LocalDate.parse(dateTime.split(" ")[0]);
        time = LocalTime.parse(dateTime.split(" ")[1]);
    }

    /**
     * Public constructor for Deadline using LocalDate and LocalTime.
     * @param description Description of the Deadline Task.
     * @param date Due date of the Deadline Task.
     * @param time Due time of the Deadline Task.
     */
    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date= date;
        this.time = time;
        this.dateTime = date.toString() + " " + time.toString();
    }

    /**
     * Getter method for the date.
     * @return Due date.
     */
    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Getter method for the time.
     * @return Due time.
     */
    public String getTime() {
        return this.time.toString();
    }

    /**
     * Getter method for String representation of the date and time.
     * @return String representation of the date and time
     */
    public String getDateTime() {
        return this.dateTime;
    }

    /**
     * Return a String representation of this Deadline.
     * @return The Deadline's icon, followed by the Task's toString, followed by the due date and time.
     */
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDate() + ", " + getTime() + ")";
    }
}
