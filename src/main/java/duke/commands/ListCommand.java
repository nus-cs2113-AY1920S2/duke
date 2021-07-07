package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import static duke.utils.Constants.LIST_COMMAND;

public class ListCommand extends Command {
    /**
     * Executes command "list".
     * Lists all the tasks inside the existing task list.
     *
     * @param tasks Task list that stores all the existing tasks.
     * @param ui Interaction with users.
     * @param storage Files related operation object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Here are the tasks in your list:");
        ui.listTasks(tasks);
    }
}
