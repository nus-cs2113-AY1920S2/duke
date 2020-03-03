package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

/**
 * ListCommand inherits from command and is the public class responsible for storing information and executing the command.
 */

public class ListCommand extends Command {

    /**
     * Constructs the ListCommand object.
     *
     * @param command the command prompt entered by the user.
     */

    public ListCommand(String command) {
        super(command);
    }

    /**
     * Executes the ListCommand and displays all the tasks in the tasklist in an ordered list.
     *
     * @param tasklist the list containing all current tasks.
     * @param ui       the object containing user interface functions.
     * @param storage  the object containing storage functions.
     */

    @Override
    public void execute(TaskList tasklist, UI ui, Storage storage) {
        ui.displayShowListMessage(tasklist.getTaskList());
    }
}
