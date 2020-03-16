package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The HelpCommand class is the Object that provides the user with help by showing the list of available commands.
 * HelpCommand implement Command interface.
 * @author Lam Yue Wei
 * @version CS2113 AY19/20 Sem 2 Duke
 */
public class HelpCommand implements Command {

    /**
     * Empty constructor for the HelpCommand.
     */
    public HelpCommand() {
    }

    /**
     * Executes the help function and shows the user the available commands.
     * @param taskList Task Manager in charge of storing the Task required to be done.
     * @param ui Ui Object that deals with interaction with the user.
     * @param storage Storage Object that deals with the loading and Storing of Tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printAvailableCommand();
    }

    /**
     * Boolean result indicate to the program if it should be exited.
     * @return False since command keyword does not match "bye".
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
