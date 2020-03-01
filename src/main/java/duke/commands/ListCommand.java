package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.exceptions.EmptyListException;
import duke.exceptions.TaskException;

/**
 * Command to list all tasks.
 */
public class ListCommand extends ExecuteCommand {

    /**
     * Lists all tasks in TaskList object.
     * @param tasks TaskList class object that handles all ArrayList of Tasks commands.
     * @param storage Storage class object that loads and saves data.
     * @throws EmptyListException If existing list is empty.
     */
    @Override
    public void execute(TaskList tasks, Storage storage ) throws EmptyListException {
        tasks.listCommand();
    }
}
