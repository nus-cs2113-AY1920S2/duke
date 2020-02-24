package duke.task;

import duke.exception.DukeNullDateException;
import duke.exception.DukeNullDescriptionException;

import static duke.util.Constants.DEADLINE_ICON;

public class Deadline extends Task {
    protected String byDate;
    protected  String typeIcon;

    public Deadline(String description, String byTime) throws DukeNullDescriptionException, DukeNullDateException {
        super(description);
        typeIcon = DEADLINE_ICON;
        if (byTime.equals("")) {
            throw new DukeNullDateException();
        }
        this.byDate = byTime;
    }

    public String getIcon() {
        return typeIcon;
    }

    public String getByDate() {
        return byDate;
    }

    @Override
    public String toString() {
        return typeIcon + super.toString() + " (by: " + byDate + ")";
    }
}
