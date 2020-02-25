package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Handles the deletion of existing tasks from the list
 */
public class DeleteCommand extends Command {

    /** Contains information related to the index of the task to be deleted */
    private String[] indexInformation;

    /**
     * Constructor for DeleteCommand Class.
     * It creates a new DeleteCommand Object with the information provided.
     *
     * @param indexInformation Contains information related to the index of the task to be deleted
     */
    public DeleteCommand(String[] indexInformation){
        this.commandType = CommandType.DeleteCommand;
        this.indexInformation = indexInformation;
    }

    /**
     * Deletes the task at the specified index by calling {@link TaskList#deleteTask(String[])}.
     *
     * @param taskList Contains the list of tasks on which the commands are executed on.
     * @throws DukeException If the index provided is invalid.
     * @see TaskList#deleteTask(String[])
     */
    @Override
    public void executeCommand(TaskList taskList) throws DukeException {
        taskList.deleteTask(indexInformation);
    }
}
