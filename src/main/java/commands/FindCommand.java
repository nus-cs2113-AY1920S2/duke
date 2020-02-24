package commands;

import exceptions.MissingDescriptionException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Command object that handles a find operation
 */
public class FindCommand extends Command {
    /**
     * Constructs a Find Command object
     * @param rawUserInput String provided by user
     */
    public FindCommand(String rawUserInput) {
        super(rawUserInput);
    }

    /**
     * Finds and prints to Ui the list of Task objects that contains the substring
     * provided by user.
     * Updates Task list and writes to Storage
     * @param taskList object handling task operations
     * @param ui object handling default user interface messages
     * @param storage object handling storage operations
     * @throws MissingDescriptionException throws when user does not provide a keyword to find
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws MissingDescriptionException {
        try {
            String[] splitCommands = removeCommandWord(super.rawUserInput);
            taskList.findTask(splitCommands[1]);
        } catch (MissingDescriptionException e) {
            ui.printErrorMessage("Missing keywords to search for! Please provide a keyword to search!");
        }
    }
}
