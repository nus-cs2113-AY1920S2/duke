package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Command object that handles the exiting of Duke
 */
public class ExitCommand extends Command {
    /**
     * Constructs a Exit Command object and sets boolean isExit to true
     * @param userInput String from user input
     */
    public ExitCommand(String userInput) {
        super(userInput);
        super.setExitTrue();
    }

    /**
     * Prints user interface message for exiting Duke
     * @param taskList object handling task operations
     * @param ui object handling default user interface messages
     * @param storage object handling storage operations
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printGoodbyeMessage();
    }
}
