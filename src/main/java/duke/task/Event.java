package duke.task;

/**
 * The Event class is a Task with specified description and date of occurrence.
 * Event class extends from Task class.
 * @author Lam Yue Wei
 * @version CS2113 AY19/20 Sem 2 Duke
 */
public class Event extends Task {
    String date;

    /**
     * Public constructor for Event.
     * @param description Description of the Event Task.
     * @param date Date of occurrence of the Event Task.
     */
    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    /**
     * Getter method for the date of occurrence.
     * @return Date of occurrence.
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Return a String representation of this Event.
     * @return The Event's icon, followed by the Task's toString, followed by the date of occurrence.
     */
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date + ")";
    }
}
