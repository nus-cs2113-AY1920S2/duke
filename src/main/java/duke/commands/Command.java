package duke.commands;

import duke.data.exception.DukeException;
import duke.data.task.TaskList;

/**
 * Represents an executable command.
 */
public abstract class Command {
    protected boolean isExit;

    protected Command() {
        isExit = false;
    }

    public void execute(TaskList tasklist) throws DukeException {
    }

    public boolean isExit() {
        return isExit;
    }
}
