package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Manages event task operations */
public class Event extends Task {

    protected String at;
    String atFormat;

    public Event(String description, String at){
        super(description);
        this.at = at;
        this.atFormat = getAtFormat();
    }

    public Event(String description, String at, Boolean isDone){
        super(description, isDone);
        this.at = at;
        this.atFormat = getAtFormat();
    }

    /**
     * Parses the String which contains the pattern yyyy-MM-dd HHmm to
     * return the string in a different format.
     *
     * @return String in a different format of MMM dd yyyy HHmm.
     */
    public String getAtFormat(){
        LocalDateTime localDateTime = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        return localDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + atFormat + ")";
    }

    @Override
    public String toText(){
        return "E | " + (this.isDone? "1" : "0") + " | " + this.description + " | " + this.at;
    }

}

