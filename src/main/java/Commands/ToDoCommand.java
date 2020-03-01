package commands;

import exceptions.MissingDescriptionException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Command Object that handles creating a new ToDo
 */
public class ToDoCommand extends Command {
    /**
     * Constructs a Todo Command object
     * @param userInput
     */
    public ToDoCommand(String userInput) {
        super(userInput);
    }

    /**
     * Creates a new ToDo object
     * Updates Task list and writes to Storage
     * @param taskList object handling task operations
     * @param ui object handling default user interface messages
     * @param storage object handling storage operations
     * @throws MissingDescriptionException throws when user fails to provide a description
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws MissingDescriptionException {
        String[] splitCommands = removeCommandWord(rawUserInput);
        taskList.addNewToDo(splitCommands[1]);
        storage.saveToHardDisk(taskList);
    }
}
