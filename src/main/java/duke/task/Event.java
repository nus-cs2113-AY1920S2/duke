package duke.task;

import duke.exception.DukeException;

public class Event extends Task {
    protected String time;
    protected  String typeIcon;

    public Event(String description, String time) throws DukeException {
        super(description);
        typeIcon = "[E]";
        this.time = time;
    }

    public String getIcon() {
        return typeIcon;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return typeIcon + super.toString() + " (at : " + time + ")";
    }
}
