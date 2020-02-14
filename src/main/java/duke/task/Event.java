package duke.task;

import duke.exception.DukeException;

import static duke.util.Constants.EVENT_ICON;

public class Event extends Task {
    protected String atTime;
    protected String typeIcon;

    public Event(String description, String atTime) throws DukeException {
        super(description);
        typeIcon = EVENT_ICON;
        this.atTime = atTime;
    }

    public String getIcon() {
        return typeIcon;
    }

    public String getAtTime() {
        return atTime;
    }

    @Override
    public String toString() {
        return typeIcon + super.toString() + " (at : " + atTime + ")";
    }
}
