package duke.command;

import duke.storage.Storage;
import duke.data.TaskList;
import duke.ui.Ui;
import duke.exception.DukeException;

/**
 * The Command Interface is an Interface that provides the platform for various Command to be executed.
 * @author Lam Yue Wei
 * @version CS2113 AY19/20 Sem 2 Duke
 */
public interface Command {
    /**
     * Abstract method that other Command Object has to override to executes their respective command function.
     * @param taskList Task Manager in charge of storing the Task required to be done.
     * @param ui Ui Object that deals with interaction with the user.
     * @param storage Storage Object that deals with the loading and Storing of Tasks.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Abstract method that other Command Object has to override to indicate to the program if it should be exited.
     * @return True if user input bye, false otherwise.
     */
    public abstract boolean isExit();
}
