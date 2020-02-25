package duke.command;

import duke.exception.DukeWritingException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Storage;

/**
 * This class handles the exit, bye or quit command, implements the Command interface.
 *
 * @author A11riseforme
 */
public class ExitCommand implements Command{
    /**
     * Return true bacause the user enters `exit`, `bye` or `quit`.
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Save the data to the file and show the goodbye message to the user.
     * Exception will be thrown if unable to save to the file.
     *
     * @param taskList the TaskList object which contains the Task objects.
     * @param ui the user interface to output message.
     * @param storage the Storage object to handle the file related operation.
     * @throws DukeWritingException exception will be thrown if error occurs when writing date to the file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeWritingException {
        storage.save(taskList);
        ui.showGoodbyeMessage();
    }
}
