package duke.commands;

import duke.data.task.TaskList;
import duke.ui.TextUi;

/**
 * Handles the task of exiting.
 */
public class ListCommand extends Command {

    /**
     * Prints the current list of tasks by calling {@link TextUi#printEmptyList()}.
     *
     * @param taskList Contains the list of tasks on which the commands are executed on.
     * @see TextUi#printEmptyList()
     */
    @Override
    public void execute(TaskList taskList) {
        if (taskList.isEmpty()) {
            TextUi.printEmptyList();
        } else {
            TextUi.printList(taskList);
        }
    }
}
