package duke.commands;

import duke.common.Messages;
import duke.data.exception.DukeException;
import duke.data.task.TaskList;
import duke.ui.TextUi;

public class DeleteCommand extends Command {
    /**
     * Contains the index of the task to be deleted.
     */
    int index;

    /**
     * Constructor for DeleteCommand Class.
     * It creates a new DeleteCommand Object with the information provided.
     *
     * @param index Contains the index of the task to be delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task at the specified index by calling {@link TextUi#printDelete(TaskList, int)}.
     *
     * @param tasklist Contains the list of tasks on which the commands are executed on.
     * @throws DukeException If the index provided is invalid.
     * @see TextUi#printDelete(TaskList, int)
     */
    @Override
    public void execute(TaskList tasklist) throws DukeException {
        if (index >= tasklist.size()) {
            throw new DukeException(Messages.MESSAGE_INDEX_OUT_OF_BOUND);
        }
        TextUi.printDelete(tasklist, index);
        tasklist.remove(index);
        int taskCounter = tasklist.size();
        TextUi.printRemaining(taskCounter);
    }
}
