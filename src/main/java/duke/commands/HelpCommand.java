package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.HelpList;
import duke.ui.UI;

/**
 * HelpCommand inherits from command and is the public class responsible for storing information and executing the command.
 */

public class HelpCommand extends Command {

    /**
     * Constructs the HelpCommand object.
     *
     * @param command the command prompt entered by the user.
     */

    public HelpCommand(String command) {
        super(command);
    }

    /**
     * Executes the HelpCommand and displays the help menu.
     *
     * @param tasklist the list containing all current tasks.
     * @param ui       the object containing user interface functions.
     * @param storage  the object containing storage functions.
     */

    @Override
    public void execute(TaskList tasklist, UI ui, Storage storage) {
        ui.displayHelpListMessage();
    }
}
