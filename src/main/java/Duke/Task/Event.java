package Duke.Task;

<<<<<<< HEAD
import Duke.Exception.DukeDateParseException;

import static Duke.Parser.ParserDate.parseStringToDate;

=======
/**
 * Class representing an Event Task.
 */
>>>>>>> branch-A-JavaDoc
public class Event extends Task {
    protected String by;
    public Event(String description, String by) throws DukeDateParseException {
        super(description);
        this.by = parseStringToDate(by);
    }

    public String getEvent(){
        return description;
    }

    @Override
    public String toString() {
        if (by.equals("")) {
            return "[E]" + super.toString();
        }
        return "[E]" + super.toString() + "( at: " + by + ")";
    }
}