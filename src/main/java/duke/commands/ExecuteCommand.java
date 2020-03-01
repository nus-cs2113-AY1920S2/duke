package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.exceptions.DukeException;


public abstract class ExecuteCommand {
    protected boolean toExit;
    protected String userData;

    public abstract void execute(TaskList tasks, Storage storage) throws DukeException;

    public boolean toExit() {
        return toExit;
    }


}
