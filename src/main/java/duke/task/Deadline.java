package duke.task;

import duke.exception.DukeException;

public class Deadline extends Task {
    protected String byTime;
    protected  String typeIcon;

    public Deadline(String description, String byTime) throws DukeException {
        super(description);
        typeIcon = "[D]";
        this.byTime = byTime;
    }

    @Override
    public String toString() {
        return typeIcon + super.toString() + " (by: " + byTime + ")";
    }
}
