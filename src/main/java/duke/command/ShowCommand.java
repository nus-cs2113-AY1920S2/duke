package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * ShowCommand class extended from Command class to deal with printing the list
 * of tasks that is saved by user
 */
public class ShowCommand extends Command {
    /** Override abstract method of Command Class.
     * execute a ShowCommand in TaskList class
     *
     * @param tasks the user's TaskList.
     * @param ui the user interface to inform them
     * @param storage the storage to save any changes.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printList();
    }
}
