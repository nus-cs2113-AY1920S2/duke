package Commands;

import Exceptions.MissingDescriptionException;
import Exceptions.MissingItemIndexException;
import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

/**
 * Command Object that handles operations to mark Task as done
 */
public class DoneCommand extends Command {
    /**
     * Constructs a Done Command object
     * @param userInput unedited String object from user
     */
    public DoneCommand(String userInput) {
        super(userInput);
    }

    /**
     * Marks Task as done
     * Updates Task list and writes to Storage
     * @param taskList object handling task operations
     * @param ui object handling default user interface messages
     * @param storage object handling storage operations
     * @throws MissingItemIndexException throws when user fails to provide index to be marked done
     * @throws NumberFormatException throws when user provides a non-integer to be marked done
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws MissingItemIndexException {
        try {
            String[] splitCommands = removeCommandWord(rawUserInput);
            int itemIndex = Integer.parseInt(splitCommands[1]) - 1;
            taskList.markTaskAsDone(itemIndex);
            storage.saveToHardDisk(taskList);
        } catch (MissingDescriptionException e) {
            throw new MissingItemIndexException(rawUserInput);
        } catch (NumberFormatException e) {
            System.out.println("Please enter an integer to be marked done");
        }
    }
}
