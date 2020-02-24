package duke.task;

import duke.exception.DukeNullDateException;
import duke.exception.DukeNullDescriptionException;

import static duke.util.Constants.EVENT_ICON;

public class Event extends Task {
    protected String atDate;
    protected String typeIcon;

    public Event(String description, String atTime) throws DukeNullDescriptionException, DukeNullDateException {
        super(description);
        typeIcon = EVENT_ICON;
        if (atTime.equals("")) {
            throw new DukeNullDateException();
        }
        this.atDate = atTime;
    }

    public String getIcon() {
        return typeIcon;
    }

    public String getAtDate() {
        return atDate;
    }

    @Override
    public String toString() {
        return typeIcon + super.toString() + " (at: " + atDate + ")";
    }
}
