package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The ListCommand class is the Object that provides the user with the list of Tasks stored.
 * ListCommand implement Command interface.
 * @author Lam Yue Wei
 * @version CS2113 AY19/20 Sem 2 Duke
 */
public class ListCommand implements Command {

    /**
     * Empty constructor for ListCommand.
     */
    public ListCommand() {
    }

    /**
     * Executes the list function and shows the user the list of Tasks stored.
     * @param taskList Task Manager in charge of storing the Task required to be done.
     * @param ui Ui Object that deals with interaction with the user.
     * @param storage Storage Object that deals with the loading and Storing of Tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.getTaskCount() == 0) {
            System.out.println("     There are currently no tasks in your list");
        } else {
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < taskList.getTaskCount(); i++) {
                System.out.println("     " + (i + 1) + "." + taskList.getTask(i).toString());
            }
        }
    }
}
