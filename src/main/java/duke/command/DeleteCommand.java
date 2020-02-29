package duke.command;

import duke.exception.DukeTaskIdInvalidException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Storage;


/**
 * This class handles the delete command, implements the Command interface.
 *
 * @author A11riseforme
 */
public class DeleteCommand implements Command {
    int taskId;

    /**
     * Default constructor.
     *
     * @param taskId the id of the task which the user wants to delete.
     */
    public DeleteCommand(int taskId) {
        this.taskId = taskId;
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
     * Delete a task from the taskList.
     *
     * @param taskList the TaskList object which contains the Task objects.
     * @param ui the user interface to output message.
     * @param storage the Storage object to handle the file related operation.
     * @throws DukeTaskIdInvalidException exception is thrown if the task id provided by the user is not valid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeTaskIdInvalidException {
        try {
            Task deletedTask = taskList.getTask(taskId);
            taskList.delete(taskId);
            ui.showDeleteTaskSuccessfulPrompt(taskList, deletedTask);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeTaskIdInvalidException();
        }
    }
}
