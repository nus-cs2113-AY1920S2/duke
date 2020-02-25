package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;
import duke.util.Storage;

/**
 * This class handles the todo command, implements the Command interface.
 *
 * @author A11riseforme
 */
public class TodoCommand implements Command{
    private String taskDescription;

    /**
     * Default constructor.
     *
     * @param taskDescription the description of the event task.
     */
    public TodoCommand(String taskDescription) {
        this.taskDescription = taskDescription;
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
     * Add an todo task to the taskList.
     *
     * @param taskList the TaskList object which contains the Task objects.
     * @param ui the user interface to output message.
     * @param storage the Storage object to handle the file related operation.
     * @throws DukeException exception is thrown if error occurs, usually because the task description is empty.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Todo todoTask = new Todo(taskDescription);
        taskList.add(todoTask);
        ui.showAddTaskSuccessfulPrompt(taskList, todoTask );
    }
}
