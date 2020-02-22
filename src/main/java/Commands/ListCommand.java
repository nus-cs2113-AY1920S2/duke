package Commands;

import Exceptions.EmptyListException;
import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

/**
 * Command object to handle listing out Task in the Task List
 */
public class ListCommand extends Command {
    /**
     * Constructs a List Command object
     * @param rawUserInput unedited String provided by user
     */
    public ListCommand(String rawUserInput) {
        super(rawUserInput);
    }

    /**
     * Prints a list of Task in current Task List
     * @param taskList object handling task operations
     * @param ui object handling default user interface messages
     * @param storage object handling storage operations
     * @throws EmptyListException throws when there is no Task in the list
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws EmptyListException {
        taskList.printTaskList();
    }
}
