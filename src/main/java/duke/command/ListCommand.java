package duke.command;

import duke.TaskList;
import duke.exception.EmptyListException;
import duke.Storage;

/**
 * Command to list all tasks in Duke.
 */
public class ListCommand extends Command {

    /**
     * Lists all tasks in TaskList.
     * @param tasks TaskList class object which handles operation involving ArrayList of Tasks
     * @param storage Storage class object which manages storing and loading of data
     * @throws EmptyListException If list command is supplied to an empty list
     */
    @Override
    public void execute(TaskList tasks, Storage storage ) throws EmptyListException {
        tasks.listTasks();
    }
}
