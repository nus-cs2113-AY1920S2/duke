package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to list all tasks in the task list to the user.
 */
public class ListCommand extends Command{

    /**
     * Constructor to create a new list command.
     *
     * @param details the parameters of the command
     */
    public ListCommand (String details) {
        super(details);
    }

    @Override
    public void executeCommand(TaskList tasks) {
        Ui.printList(tasks);
    }

}
