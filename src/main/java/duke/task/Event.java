package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class is a Task with specified description and date of occurrence.
 * Event class extends from Task class.
 * @author Lam Yue Wei
 * @version CS2113 AY19/20 Sem 2 Duke
 */
public class Event extends Task {
    String dateTime;
    LocalDate date;
    LocalTime time;


    /**
     * Public constructor for Event.
     * @param description Description of the Event Task.
     * @param dateTime Date and time of occurrence of the Event Task.
     */
    public Event(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
        date = LocalDate.parse(dateTime.split(" ")[0]);
        time = LocalTime.parse(dateTime.split(" ")[1]);
    }

    /**
     * Public constructor for Event using LocalDate and LocalTime.
     * @param description Description of the Event Task.
     * @param date Date of occurrence of the Event Task.
     * @param time Time of occurrence of the Event Task.
     */
    public Event(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date= date;
        this.time = time;
        this.dateTime = date.toString() + " " + time.toString();
    }

    /**
     * Getter method for the date of occurrence.
     * @return Date of occurrence.
     */
    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Getter method for the time of occurrence.
     * @return Time of occurrence.
     */
    public String getTime() {
        return this.time.toString();
    }

    /**
     * Return a String representation of this Event.
     * @return The Event's icon, followed by the Task's toString, followed by the date and time of occurrence.
     */
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getDate() + ", " + getTime() + ")";
    }
}
