package duke.task;

import duke.enumerations.Day;

public class Event extends Task {
    
    private String at;
    private final String PREFIX = "E";
    private TimeFormat timeFormat;
    
    public Event(String description, String at) {
        super(description);
        timeFormat = new TimeFormat();
        this.at = at.replace("at", "").trim();
        this.at = this.at.replaceAll("(\\.)|(/)", "-");
        this.at = removeEnum(this.at);
        this.at = timeFormat.checkDay(this.at).trim();
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
        return "[" + PREFIX + "]" + super.toString() + " (by: " + timeFormat.date + " " + timeFormat.day + " " + at +
                ")";
    }
    
    @Override
    public String toStorage() {
        return PREFIX + super.toStorage() + PIPE + timeFormat.date + " " + timeFormat.day + " " + at;
    }
    
}
