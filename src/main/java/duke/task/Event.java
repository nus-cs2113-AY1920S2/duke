package duke.task;

import duke.exception.DukeNullDateException;
import duke.exception.DukeNullDescriptionException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static duke.util.Constants.EVENT_ICON;

public class Event extends Task {
    protected LocalDate atDate;
    protected String typeIcon;

    public Event(String description, LocalDate atDate) throws DukeNullDescriptionException {
        super(description);
        typeIcon = EVENT_ICON;
        this.atDate = atDate;
    }

    public String getIcon() {
        return typeIcon;
    }

    public LocalDate getAtDate() {
        return atDate;
    }

    @Override
    public String toString() {
        return typeIcon + super.toString() + " (at: " + atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
