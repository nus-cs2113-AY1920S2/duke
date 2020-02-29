package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to return a list of tasks containing the user-specified keyword to the user.
 */
public class FindCommand extends Command {

    /**
     * Constructor to create a new find command.
     *
     * @param details the parameters of the command
     */
    public FindCommand(String details) {
        super(details);
    }

    @Override
    public void executeCommand(TaskList tasks) {
        String keyword = this.details;
        Ui.printFindList(tasks, keyword);
    }
}
