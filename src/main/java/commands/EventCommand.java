package commands;

import exceptions.MissingDateException;
import exceptions.MissingDescriptionException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Command object that handles creating a new Event
 */
public class EventCommand extends Command {
    /**
     * Constructs a Event Command object
     * @param rawUserInput String provided by user
     */
    public EventCommand(String rawUserInput) {
        super(rawUserInput);
    }

    /**
     * Creates a new Event object
     * Updates Task list and writes to Storage
     * @param taskList object handling task operations
     * @param ui object handling default user interface messages
     * @param storage object handling storage operations
     * @throws MissingDescriptionException throws when user fails to provide description
     * @throws MissingDateException throws when user fails to provide date
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws MissingDescriptionException, MissingDateException {
        String[] splitCommands = removeCommandWord(super.rawUserInput);
        String[] eventSplit = splitDate(splitCommands[1]);
        taskList.addNewEvent(eventSplit[0].trim(), eventSplit[1].trim());
        storage.saveToHardDisk(taskList);
    }
}
