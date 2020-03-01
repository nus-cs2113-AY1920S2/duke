package commands;

import exceptions.MissingDescriptionException;
import exceptions.MissingItemIndexException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Command object that handles deleting a Task
 */
public class DeleteCommand extends Command {
    /**
     * Constructs a Delete Command object
     * @param rawUserInput unedited String from user
     */
    public DeleteCommand(String rawUserInput) {
        super(rawUserInput);
    }

    /**
     * Deletes a Task
     * Updates Task list and writes to Storage
     * @param taskList object handling task operations
     * @param ui object handling default user interface messages
     * @param storage object handling storage operations
     * @throws MissingItemIndexException throws when user fails to provide an index to be deleted
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws MissingItemIndexException {
        try {
            String[] splitCommands = removeCommandWord(rawUserInput);
            int itemIndex = Integer.parseInt(splitCommands[1]) - 1;
            taskList.deleteTask(itemIndex);
            storage.saveToHardDisk(taskList);
        } catch (MissingDescriptionException e) {
            throw new MissingItemIndexException(rawUserInput);
        } catch (NumberFormatException e) {
            ui.printErrorMessage("Please enter an integer to be marked done");
        }
    }
}