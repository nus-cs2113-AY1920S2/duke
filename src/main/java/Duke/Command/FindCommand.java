package Duke.Command;

import Duke.Tasks.Task;
import Duke.TaskList;
import Duke.Storage;
import Duke.DukeException;
import Duke.UI.Messages;
import Duke.UI.Ui;

import java.util.ArrayList;

/**
 * Command that searches for tasks containing specific keyword.
 */
public class FindCommand extends Command {

    protected String fullCommand;

    /**
     * Constructor of FindCommand.
     *
     * @param fullCommand entire input from user.
     */
    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Gets the keyword specified by the user.
     *
     * @return the keyword to search the list for.
     */
    public String getKeyword() {
        return fullCommand.substring(5);
    }

    /**
     * Search for tasks that contain the given keyword.
     *
     * @return a new list containing all the tasks that contain the keyword.
     * @throws DukeException if the keyword cannot be found in any of the tasks in the list.
     */
    public ArrayList<Task> searchKeyword() throws DukeException {
        String keyword = getKeyword();
        ArrayList<Task> listCopy = new ArrayList<Task>();

        for (int i = 0; i < TaskList.getSize(); ++i) {
            Task task = TaskList.fetchTask(i);
            String stringToSearch = task.action;
            if (stringToSearch.contains(keyword)) {
                listCopy.add(task);
            }
        }

        if (listCopy.size() == 0) {
            throw new DukeException();
        }

        return listCopy;
    }

    /**
     * Displays the new list.
     *
     * @param listCopy list of tasks containing the keyword.
     */
    public void printOutList (ArrayList<Task> listCopy) {
        for (int j = 0; j < listCopy.size(); ++j) {
            Ui.out.println(Integer.toString(j+1) + ". " + listCopy.get(j));
        }
    }

    /**
     * Executes the command.
     *
     * @param tasks object of TaskList class containing list of tasks in the program.
     * @param ui object of Ui class handling printing output to the user.
     * @param storage object of Storage class for saving program to file.
     */
    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        try {
            ArrayList<Task> listCopy = searchKeyword();
            ui.out.println(Messages.MESSAGE_FIND_SUCCESS);
            printOutList(listCopy);
        } catch (IndexOutOfBoundsException e) {
            ui.out.println(Messages.FIND_ERROR_MESSAGE);
        } catch (DukeException e) {
            ui.out.println(Messages.FIND_ERROR_MESSAGE);
        }
    }

    /**
     * Indicates program not ready to exit.
     *
     * @return isExit() is false and program should continue running.
     */
    @Override
    public boolean isExit(){
        return false;
    };

}
