package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * List all tasks in the task list to the user.
 */
public class ListCommand extends Command{

    public ListCommand (String details) {
        super(details);
    }

    @Override
    public void executeCommand(TaskList tasks) {
        Ui.printList(tasks);
    }

}
