package duke.task;

import duke.enumerations.Day;
import duke.enumerations.Month;

/**
 * Represent the Deadline object
 */
public class Deadline extends Task {
    
    private String by;
    private final String PREFIX = "D";
    private TimeFormat timeFormat;
    
    /**
     * Constructor for Deadline
     *
     * @param description the description of the task
     * @param by          the timeline for the task to be completed
     */
    public Deadline(String description, String by) {
        super(description);
        timeFormat = new TimeFormat();
        this.by = by.replace("by", "").trim();
        this.by = this.by.replaceAll("(\\.)|(/)", "-");
        this.by = Day.removeEnum(this.by);
        this.by = Month.changeMonthToNumber(this.by);
        this.by = timeFormat.checkDay(this.by).trim();
    }
    
    /**
     * Return the information as a string
     *
     * @return the string of information relevant to the object
     */
    @Override
    public String toString() {
        if (this.by.contains("Invalid")) {
            return "[" + PREFIX + "]" + super.toString() + " (by: " + by + ")";
        }
        return "[" + PREFIX + "]" + super.toString() + " (by: " + timeFormat.date + " " + timeFormat.day + " " + by +
                ")";
    }
    
    /**
     * Return the information with a specific format to the storage
     *
     * @return the string of information relevant to the object
     */
    @Override
    public String toStorage() {
        if (this.by.contains("Invalid")) {
            return PREFIX + super.toStorage() + PIPE + by;
        }
        return PREFIX + super.toStorage() + PIPE + timeFormat.date + " " + timeFormat.day + " " + by;
    }
    
}
