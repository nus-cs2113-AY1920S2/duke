package duke.command;

import duke.exception.DukeException;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Storage;

/**
 * This class handles the event command, implements the Command interface.
 *
 * @author A11riseforme
 */
public class EventCommand implements Command{
    private String taskDescription;
    private String atDate;

    /**
     * Constructor with two arguments.
     *
     * @param taskDescription the description of the event task.
     * @param atDate the date of the event task.
     */
    public EventCommand(String taskDescription, String atDate) {
        this.taskDescription = taskDescription;
        this.atDate = atDate;
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
     * Add an event task to the taskList.
     *
     * @param taskList the TaskList object which contains the Task objects.
     * @param ui the user interface to output message.
     * @param storage the Storage object to handle the file related operation.
     * @throws DukeException exception is thrown if error occurs, usually because the task description or date is empty.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Event eventTask = new Event(taskDescription, atDate);
        taskList.add(eventTask);
        ui.showAddTaskSuccessfulPrompt(taskList, eventTask);
    }
}
