package commands;

import exceptions.MissingDescriptionException;
import exceptions.MissingItemIndexException;
import exceptions.TaskAlreadyDoneException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Command Object that handles operations to mark Task as done
 */
public class DoneCommand extends Command {
    /**
     * Constructs a Done Command object
     * @param rawUserInput unedited String from user
     */
    public DoneCommand(String rawUserInput) {
        super(rawUserInput);
    }

    /**
     * Marks Task as done
     * Updates Task list and writes to Storage
     * @param taskList object handling task operations
     * @param ui object handling default user interface messages
     * @param storage object handling storage operations
     * @throws MissingItemIndexException throws when user fails to provide index to be marked done
     * @throws NumberFormatException throws when user provides a non-integer to be marked done
     * @throws TaskAlreadyDoneException throws when user tries to mark a task that is already done
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws MissingItemIndexException
            , TaskAlreadyDoneException {
        try {
            String[] splitCommands = removeCommandWord(super.rawUserInput);
            int itemIndex = Integer.parseInt(splitCommands[1]) - 1;
            taskList.markTaskAsDone(itemIndex);
            storage.saveToHardDisk(taskList);
        } catch (MissingDescriptionException e) {
            throw new MissingItemIndexException(super.rawUserInput);
        } catch (NumberFormatException e) {
            ui.printErrorMessage("Please enter an integer to be marked done");
        }
    }
}