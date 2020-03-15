package duke.commands;

import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.taskManager.Task;
import duke.ui.DisplayUI;

import static duke.constants.Constants.BYE_MESSAGE;
import static duke.constants.Constants.LINE_DIVIDER;

import java.util.ArrayList;

/**
 * A command object to exit the program
 */
public class ExitCommand implements Command {

    /**
     * Exit command to exit the program
     *
     * @param function String containing "bye"
     * @param ui       ui object for printing statements
     * @param storage  Storage object for accessing and modifying tasklist.txt
     * @param taskList Array of tasks stored (Not in use currently)
     * @param tasks    Array of tasks stored (In Use)
     * @return boolean false to main function
     */
    public boolean execute(String function, DisplayUI ui, Storage storage, TaskList taskList, ArrayList<Task> tasks) {
        ui.printToUser(BYE_MESSAGE + LINE_DIVIDER);
        return false;
    }
}
