package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public abstract class Command {
    public abstract boolean isExit();
    public abstract void execute(TaskList tasks, Ui ui) throws DukeException;
}
