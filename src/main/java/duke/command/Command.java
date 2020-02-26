package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * the abstract command class(superclass of other command-related class(ExitCommand, HelpCommand etc.))
 * has some abstract methods
 */
public abstract class Command {
    public abstract boolean isExit();
    public abstract void execute(TaskList tasks, Ui ui) throws DukeException;
}
