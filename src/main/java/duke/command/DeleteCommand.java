package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The DeleteCommand class is the Object that delete a Task from the TaskList.
 * DeleteCommand implement Command interface.
 * @author Lam Yue Wei
 * @version CS2113 AY19/20 Sem 2 Duke
 */
public class DeleteCommand implements Command {
    private int deleteIndex;

    /**
     * Public constructor for DeleteCommand.
     * @param deleteIndex Index of Task that is completed.
     */
    public DeleteCommand(int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }

    /**
     * Executes the delete function and delete a Task from the TaskList.
     * @param taskList Task Manager in charge of storing the Task required to be done.
     * @param ui Ui Object that deals with interaction with the user.
     * @param storage Storage Object that deals with the loading and Storing of Tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (deleteIndex >= taskList.getTaskCount() | deleteIndex < 0) {
            System.out.println("     :( OOPS!!! Invalid index for delete.");
        } else{
            System.out.println("     Noted. I've removed this task:");
            System.out.println("       " + taskList.getTask(deleteIndex));
            taskList.deleteTask(deleteIndex);
            System.out.println("     Now you have " + taskList.getTaskCount() + " tasks in the list.");
        }
    }

    /**
     * Boolean result indicate to the program if it should be exited.
     * @return False since command keyword does not match "bye".
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
