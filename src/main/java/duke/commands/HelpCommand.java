package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class HelpCommand extends Command {
    /**
     * Executes command "help".
     * Displays all valid commands to the user.
     * 
     * @param tasks Task list that stores all the existing tasks.
     * @param ui Interaction with users.
     * @param storage Files related operation object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayHelpMessage();
    }
}
