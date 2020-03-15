package duke.commands;

import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.taskManager.Task;
import duke.ui.DisplayUI;

import java.util.ArrayList;

/**
 * Command to print error message for wrong input
 */
public class InputErrorCommand implements Command {

    /**
     * Command object to print an error message for wrong input
     *
     * @param function String containing unknown function input
     * @param ui       ui object for printing statements
     * @param storage  Storage object for accessing and modifying tasklist.txt
     * @param taskList Array of tasks stored (Not in use currently)
     * @param tasks    Array of tasks stored (In Use)
     * @return boolean true to main function
     */
    public boolean execute(String function, DisplayUI ui, Storage storage, TaskList taskList, ArrayList<Task> tasks) {
        ui.showErrorInput();
        return true;
    }
}
