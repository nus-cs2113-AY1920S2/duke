package duke.commands;

import duke.common.Messages;
import duke.data.exception.DukeException;
import duke.data.task.Task;
import duke.data.task.TaskList;
import duke.storage.Storage;
import duke.ui.TextUi;

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

