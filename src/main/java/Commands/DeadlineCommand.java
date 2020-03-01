package Commands;

import Exceptions.MissingDateException;
import Exceptions.MissingDescriptionException;
import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

/**
 * Command object that handles creating a new Deadline
 */
public class DeadlineCommand extends Command {
    /**
     * Constructs a Deadline Object
     * @param userInput unedited String object provided by user
     */
    public DeadlineCommand(String userInput) {
        super(userInput);
    }

    /**
     * Creates a new Deadline
     * Updates Task list and writes to Storage
     * @param taskList object handling task operations
     * @param ui object handling default user interface messages
     * @param storage object handling storage operations
     * @throws IndexOutOfBoundsException throws when user fails to provide date/description
     * @throws MissingDescriptionException throws when user fails to provide description
     * @throws MissingDateException throws when user fails to provide date
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws IndexOutOfBoundsException, MissingDescriptionException, MissingDateException {
        String[] splitCommands = removeCommandWord(rawUserInput);
        String[] deadlineSplit = splitDate(splitCommands[1]);
        taskList.addNewDeadline(deadlineSplit[0].trim(), deadlineSplit[1].trim());
        storage.saveToHardDisk(taskList);
    }
}
