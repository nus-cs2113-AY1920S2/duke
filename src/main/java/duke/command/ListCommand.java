package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Storage;

/**
 * This class handles the list command, implements the Command interface.
 *
 * @author A11riseforme
 */
public class ListCommand implements Command {
    /**
     * Return false because user does not want to exit the programme.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * show all the task the user has added.
     *
     * @param taskList the TaskList object which contains the Task objects.
     * @param ui the user interface to output message.
     * @param storage the Storage object to handle the file related operation.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showList(taskList);
    }
}
