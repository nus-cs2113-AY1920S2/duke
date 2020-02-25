package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Storage;

import java.util.ArrayList;

/**
 * This class handles the find command, implements the Command interface.
 *
 * @author A11riseforme
 */
public class FindCommand implements Command {
    private String searchTerm;

    /**
     * Default constructor
     *
     * @param searchTerm the search pattern provided by the user.
     */
    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    /**
     * Return false because user does not want to exit the programme.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * show all the task with whose description contains the search term the user provided.
     *
     * @param taskList the TaskList object which contains the Task objects.
     * @param ui the user interface to output message.
     * @param storage the Storage object to handle the file related operation.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task i: taskList.getList()) {
            if (i.getTaskDescription().toLowerCase().contains(searchTerm.toLowerCase())) {
                foundTasks.add(i);
            }
        }
        ui.showFind(foundTasks);
    }
}
