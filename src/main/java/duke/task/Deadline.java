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
        this.by = removeEnum(this.by);
        this.by = changeMonthToNumber(this.by);
        this.by = timeFormat.checkDay(this.by).trim();
    }
    
    /**
     * Remove the constant Day that user or storage load up for sync purpose
     *
     * @param input the string of the day, time and other information related
     * @return string that does not contain Day constant value
     */
    private String removeEnum(String input) {
        Day[] days = Day.values();
        for (Day day : days) {
            if (input.contains(day.toString().toLowerCase())) {
                input = input.replace(day.toString().toLowerCase(), "");
                break;
            }
        }
        return input;
    }
    
    /**
     * Change the shorthand of Month to number, e.g. Jan - 01, ..., Dec - 12
     *
     * @param input the date and time given by user
     * @return the shorthand of Month to number
     */
    private String changeMonthToNumber(String input) {
        Month[] months = Month.values();
        for (Month month : months) {
            if (input.contains(month.toString())) {
                input = input.replace(month.toString(), month.getNumber());
                break;
            }
        }
        return input;
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



