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
        this.at = removeEnum(this.at);
        this.at = changeMonthToNumber(this.at);
        this.at = timeFormat.checkDay(this.at).trim();
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



