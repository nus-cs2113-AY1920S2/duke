package duke.command;

import duke.TaskList;
import duke.exception.DukeException;
import duke.Ui;


public abstract class Command {
    protected String userInput;

    abstract public void execute(TaskList tasks, Ui ui) throws DukeException;


}
