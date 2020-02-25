package duke.task;

import duke.enumerations.Day;

/**
 * Represent the Event object
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
        this.at = at.replace("at", "").trim();
        this.at = this.at.replaceAll("(\\.)|(/)", "-");
        this.at = removeEnum(this.at);
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
     * Return the information as a string
     *
     * @return the string of information relevant to the object
     */
    @Override
    public String toString() {
        return "[" + PREFIX + "]" + super.toString() + " (by: " + timeFormat.date + " " + timeFormat.day + " " + at +
                ")";
    }
    
    /**
     * Return the information with a specific format to the storage
     *
     * @return the string of information relevant to the object
     */
    @Override
    public String toStorage() {
        return PREFIX + super.toStorage() + PIPE + timeFormat.date + " " + timeFormat.day + " " + at;
    }
    
}
