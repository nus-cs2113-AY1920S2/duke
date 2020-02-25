package duke.task;

import duke.enumerations.Day;

public class Deadline extends Task {
    
    private String by;
    private final String PREFIX = "D";
    private TimeFormat timeFormat;
    
    public Deadline(String description, String by) {
        super(description);
        timeFormat = new TimeFormat();
        this.by = by.replace("by", "").trim();
        this.by = this.by.replaceAll("(\\.)|(/)", "-");
        this.by = removeEnum(this.by);
        this.by = timeFormat.checkDay(this.by).trim();
    }
    
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
    
    @Override
    public String toString() {
        return "[" + PREFIX + "]" + super.toString() + " (by: " + timeFormat.date + " " + timeFormat.day + " " + by +
                ")";
    }
    
    @Override
    public String toStorage() {
        return PREFIX + super.toStorage() + PIPE + timeFormat.date + " " + timeFormat.day + " " + by;
    }
    
}



