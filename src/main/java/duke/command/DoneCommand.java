package duke.command;

import duke.exception.DukeTaskIdInvalidException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Storage;

/**
 * This class handles the done command, implements the Command interface.
 *
 * @author A11riseforme
 */
public class DoneCommand implements Command {
    int taskId;

    /**
     * Default constructor.
     *
     * @param taskId the id of the task which the user wants to mark as done.
     */
    public DoneCommand(int taskId) {
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
     * mark a task from the taskList as done.
     *
     * @param taskList the TaskList object which contains the Task objects.
     * @param ui the user interface to output message.
     * @param storage the Storage object to handle the file related operation.
     * @throws DukeTaskIdInvalidException exception is thrown if the task id provided by the user is not valid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeTaskIdInvalidException {
        try {
            taskList.getTask(taskId).markAsDone();
            ui.showMarkAsDoneSuccessfulPrompt(taskList.getTask(taskId));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeTaskIdInvalidException();
        }
    }
}
