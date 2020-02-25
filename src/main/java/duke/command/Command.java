package duke.command;

import duke.exception.DukeException;
import duke.exception.DukeWritingException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Storage;

/**
 * This is a command interface to handle the user's actual command.
 *
 * @author A11riseforme
 */
public interface Command {
    /**
     * Decide whether the user wants to stop the programme.
     *
     * @return return true if the user enters `exit`, `bye` or `quit`.
     */
    boolean isExit();

    /**
     * Execute the actual command.
     *
     * @param taskList the Tasklist object which contains the Task objects.
     * @param ui the user interface to output message.
     * @param storage the Storage object to handle the file related operation.
     * @throws DukeException exception is thrown when error occurs during the runtime.
     */
    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
