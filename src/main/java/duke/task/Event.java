package duke.task;

import duke.enumerations.Day;
import duke.enumerations.Month;

/**
 * Represent the Deadline object
 */
public class Event extends Task {
    
    private String at;
    private final String PREFIX = "E";
    private TimeFormat timeFormat;
    
    /**
     * Constructor for Event
     *
     * @param description the description of the task
     * @param at          the timeline for the task to be completed
     */
    public Event(String description, String at) {
        super(description);
        timeFormat = new TimeFormat();
        this.at = at.replaceFirst("at", "").trim();
        this.at = this.at.replaceAll("(\\.)|(/)", "-");
        this.at = Day.removeEnum(this.at);
        this.at = Month.changeMonthToNumber(this.at);
        this.at = timeFormat.checkDay(this.at).trim();
    }
    
    /**
     * Return the information as a string
     *
     * @return the string of information relevant to the object
     */
    @Override
    public String toString() {
        if (this.at.contains("Invalid")) {
            return "[" + PREFIX + "]" + super.toString() + " (at: " + at + ")";
        }
        return "[" + PREFIX + "]" + super.toString() + " (at: " + timeFormat.date + " " + timeFormat.day + " " + at +
                ")";
    }
    
    /**
     * Return the information with a specific format to the storage
     *
     * @return the string of information relevant to the object
     */
    @Override
    public String toStorage() {
        if (this.at.contains("Invalid")) {
            return PREFIX + super.toStorage() + PIPE + at;
        }
        return PREFIX + super.toStorage() + PIPE + timeFormat.date + " " + timeFormat.day + " " + at;
    }
    
}
