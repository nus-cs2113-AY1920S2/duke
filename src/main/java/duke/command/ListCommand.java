package duke.command;

import duke.common.DukeException;
import duke.tasklist.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Deals with command related to list all tasks.
 */
public class ListCommand extends Command {
    /**
     * Lists all tasks and asks ui to show tasks.
     *
     * @param tasks stores all tasks.
     * @param ui deals with user interface.
     * @param storage deals with back up file, useless here.
     * @throws DukeException exists since there is no task in list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("\tCurrently, there is no task in the list!");
        }
        ui.showListOfTasks(tasks);
    }
}
