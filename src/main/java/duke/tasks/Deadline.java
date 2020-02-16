package duke.tasks;

import duke.DukeException;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) throws DukeException {
        super(description.trim());
        if(description.equals("") || by.equals(""))
        {
            throw new DukeException();
        }
        this.by = by.trim();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
