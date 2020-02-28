package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Manages deadline task operations */
public class Deadline extends Task {

    protected String by;
    private String byFormat;


    public Deadline (String description, String by) {
        super(description);
        this.by = by;
        this.byFormat = getByFormat();
    }

    public Deadline (String description, String by, Boolean isDone) {
        super(description, isDone);
        this.by = by;
        this.byFormat = getByFormat();
    }

    public String getByFormat() {
        LocalDateTime localDateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        return localDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byFormat + ")";
    }

    @Override
    public String toText() {
        return "D | " + (this.isDone? "1" : "0") + " | " + this.description + " | " + this.by;
    }
}
