package duke.task;
import duke.exception.DukeException;

import static duke.util.Constants.*;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) throws DukeException {
        if (description.length() > 0) {
            this.description = description;
            this.isDone = false;
        } else {
            throw new DukeException();
        }
    }

    public String getStatusIcon() {
        return (isDone ? yesIcon : noIcon); //return tick or X symbols
    }

    public String getTaskDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + getTaskDescription();
    }
}