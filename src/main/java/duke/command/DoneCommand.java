package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Handles the deletion of existing tasks from the list.
 */
public class DoneCommand extends Command {

    /** Contains information related to the index of the task to be deleted */
    private String[] indexInformation;

    /**
     * Constructor for DoneCommand Class.
     * It creates a new DoneCommand Object with the information provided.
     *
     * @param indexInformation Contains information related to the index of the task to be deleted
     */
    public DoneCommand(String[] indexInformation) {
        this.commandType = CommandType.DoneCommand;
        this.indexInformation = indexInformation;
    }

    /**
     * Marks the task at the specified index by calling {@link TaskList#markTaskAsDone(String[])}.
     *
     * @param taskList Contains the list of tasks on which the commands are executed on.
     * @throws DukeException If the index provided is invalid.
     * @see  TaskList#markTaskAsDone(String[])
     */
    @Override
    public void executeCommand(TaskList taskList) throws DukeException {
        taskList.markTaskAsDone(indexInformation);
    }
}
