package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

/**
 * ByeCommand inherits from command and is the public class responsible for storing information and executing the command.
 */

public class ByeCommand extends Command {

    /**
     * Constructs the ByeCommand object.
     * @param command the command prompt entered by the user.
     */

    public ByeCommand(String command) {
        super(command);
    }

    /**
     * Executes the ByeCommand and exits the application.
     * @param tasklist the list containing all current tasks.
     * @param ui the object containing user interface functions.
     * @param storage the object containing storage functions.
     */

    @Override
    public void execute(TaskList tasklist, UI ui, Storage storage) {
        ui.displayGoodbyeMessage();
        ui.setExitStatus(true);
    }
}
