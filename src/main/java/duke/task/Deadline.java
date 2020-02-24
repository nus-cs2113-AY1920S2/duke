package duke.task;

import duke.exception.DukeException;

import static duke.util.Constants.DEADLINE_ICON;

public class Deadline extends Task {
    protected String byTime;
    protected  String typeIcon;

    public Deadline(String description, String byTime) throws DukeException {
        super(description);
        typeIcon = DEADLINE_ICON;
        this.byTime = byTime;
    }

    public String getIcon() {
        return typeIcon;
    }

    public String getByTime() {
        return byTime;
    }

    @Override
    public String toString() {
        return typeIcon + super.toString() + " (by: " + byTime + ")";
    }
}
