package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.exceptions.ChatboxException;

public class ExitCommand extends Command {
    /**
     * Executes the command "bye".
     * Marks that this command represent "Exit".
     * 
     * @param tasks Task list that stores all the existing tasks.
     * @param ui Interaction with users.
     * @param storage Files related operation object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        this.isExit = true;
        ui.displayExitMessage();
    }
}
