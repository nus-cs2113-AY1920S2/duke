package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.UI;

import java.util.ArrayList;
import java.util.List;

/**
 * FindCommand inherits from command and is the public class responsible for storing information and executing the command.
 */

public class FindCommand extends Command {

    /**
     * The substring used to find matching tasks entered by the user.
     */

    protected String substring;

    /**
     * Constructs the FindCommand object.
     * @param command the command prompt entered by the user.
     * @param substring the substring used to find matching tasks entered by the user.
     */

    public FindCommand(String command, String substring) {
        super(command);
        this.substring = substring;
    }

    /**
     * Executes the FindCommand and searches for tasks with descriptions matching the substring,
     * displaying these tasks in an ordered list.
     * @param tasklist the list containing all current tasks.
     * @param ui the object containing user interface functions.
     * @param storage the object containing storage functions.
     */

    @Override
    public void execute(TaskList tasklist, UI ui, Storage storage) {
        List<Task> foundList = new ArrayList<Task>();
        for (Task task : tasklist.getTaskList()) {
            if (task.getDescription().contains(this.substring)) {
                foundList.add(task);
            }
        }
        if (foundList.isEmpty()) {
            ui.displayUnableToFindTaskMessage();
        } else {
            ui.displayFoundTaskMessage(foundList);
        }
    }
}
