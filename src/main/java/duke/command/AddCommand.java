package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.TaskType;
import duke.ui.Ui;

/**
 * Handles the addition of new tasks to the list.
 */
public class AddCommand extends Command {

    /** Contains information related to the task. */
    private String[] taskInformation;
    /** Denotes whether the command contains a single word. */
    private boolean isOneWordCommand;
    /** Denotes the type of the task to be added. */
    private TaskType taskType;

    /**
     * Constructor for AddCommand Class.
     * It creates a new AddCommand Object with the information provided.
     *
     * @param taskInformation Contains information related to the task
     * @param isOneWordCommand Denotes whether the command contains a single word
     * @param taskType Denotes the type of the task to be added
     */
    public AddCommand(String[] taskInformation, boolean isOneWordCommand, TaskType taskType) {
        this.commandType = CommandType.AddCommand;
        this.taskInformation = taskInformation;
        this.isOneWordCommand = isOneWordCommand;
        this.taskType = taskType;
    }

    /**
     * Adds a new task to the list with the information provided by calling
     * {@link TaskList#addToDoTask(String[], boolean)} (or) {@link TaskList#addDeadlineTask(String[], boolean)}
     * (or) {@link TaskList#addEventTask(String[], boolean)} as required.
     *
     * @param taskList Contains the list of tasks on which the commands are executed on.
     * @throws DukeException If the format used to add a task isn't valid.
     * @see TaskList#addToDoTask(String[], boolean)
     * @see TaskList#addDeadlineTask(String[], boolean)
     * @see TaskList#addEventTask(String[], boolean)
     */
    @Override
    public void executeCommand(TaskList taskList) throws DukeException {
        switch (taskType) {
        case ToDo:
            taskList.addToDoTask(taskInformation,isOneWordCommand);
            break;
        case Deadline:
            taskList.addDeadlineTask(taskInformation,isOneWordCommand);
            break;
        case Event:
            taskList.addEventTask(taskInformation,isOneWordCommand);
            break;
        default:
            Ui.printInvalidCommand();
            break;
        }
    }
}
