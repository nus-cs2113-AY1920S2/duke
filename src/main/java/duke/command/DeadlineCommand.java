package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Storage;

/**
 * This class handles the deadline command, implements the Command interface.
 *
 * @author A11riseforme
 */
public class DeadlineCommand implements Command {
    private String taskDescription;
    private String byDate;

    /**
     * Constructor with two arguments.
     *
     * @param taskDescription the description of the deadline task.
     * @param byDate the date of the deadline task.
     */
    public DeadlineCommand(String taskDescription, String byDate) {
        this.taskDescription = taskDescription;
        this.byDate = byDate;
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
     * Add a deadline task to the taskList.
     *
     * @param taskList the TaskList object which contains the Task objects.
     * @param ui the user interface to output message.
     * @param storage the Storage object to handle the file related operation.
     * @throws DukeException exception is thrown if error occurs, usually because the task description or date is empty.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Deadline deadlineTask = new Deadline(taskDescription, byDate);
        taskList.add(deadlineTask);
        ui.showAddTaskSuccessfulPrompt(taskList, deadlineTask);
    }
}
