package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Storage;

/**
 * This class handles the help command, implements the Command interface.
 *
 * @author A11riseforme
 */
public class HelpCommand implements Command {
    private String commandWord;

    /**
     * Default constructor.
     *
     * @param commandWord the string after the `help` command.
     */
    public HelpCommand(String commandWord) {
        this.commandWord = commandWord;
    }

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
     * Show the help message to the user. if no command specified, show all the commands.
     *
     * @param taskList the TaskList object which contains the Task objects.
     * @param ui the user interface to output message.
     * @param storage the Storage object to handle the file related operation.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showHelpMessage(commandWord);
    }
}
