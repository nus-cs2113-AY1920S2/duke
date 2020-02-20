package duke.command;

import duke.TaskList;
import duke.exception.DukeException;
import duke.Storage;


public abstract class Command {
    protected String userInput;

    abstract public void execute(TaskList tasks, Storage storage) throws DukeException;


}
