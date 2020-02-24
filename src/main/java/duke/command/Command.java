package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

public abstract class Command {

    protected CommandType commandType;

    public abstract void executeCommand(TaskList taskList) throws DukeException;
}
