package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The DoneCommand class is the Object that mark a Task in the TaskList as completed.
 * DoneCommand implement Command interface.
 * @author Lam Yue Wei
 * @version CS2113 AY19/20 Sem 2 Duke
 */
public class DoneCommand implements Command {
    private int doneIndex;

    /**
     * Public constructor for TodoCommand.
     * @param doneIndex Index of Task that is completed.
     */
    public DoneCommand(int doneIndex) {
        this.doneIndex = doneIndex;
    }

    /**
     * Executes the done function and mark a Task in the TaskList as completed.
     * @param taskList Task Manager in charge of storing the Task required to be done.
     * @param ui Ui Object that deals with interaction with the user.
     * @param storage Storage Object that deals with the loading and Storing of Tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (doneIndex >= taskList.getTaskCount() || doneIndex < 0) {
            System.out.println("     :( OOPS!!! Invalid index for done.");
        } else {
            taskList.getTask(doneIndex).markAsDone();
            System.out.println("     Nice! I've marked this task as done:");
            System.out.println("       " + taskList.getTask(doneIndex).toString());
        }
    }
}
