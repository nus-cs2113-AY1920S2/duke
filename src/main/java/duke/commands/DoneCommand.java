package duke.commands;

import duke.common.Messages;
import duke.data.exception.DukeException;
import duke.data.task.TaskList;
import duke.ui.TextUi;

public class DoneCommand extends Command {

    /** Contains the index of the task to be done. */
    int index;

    /**
     * Constructor for DoneCommand Class.
     * It creates a new DoneCommand Object with the information provided.
     *
     * @param index Contains the index of the task to be done.
     */
    public DoneCommand(int index) {
        this.index = index;
    }


    /**
     * Marks the task at the specified index by calling {@link TextUi#printDone(TaskList, int)}.
     *
     * @param tasklist Contains the list of tasks on which the commands are executed on.
     * @throws DukeException If the index provided is invalid.
     * @see  TextUi#printDone(TaskList, int)
     */
    @Override
    public void execute(TaskList tasklist) throws DukeException {
        if (index >= tasklist.size()) {
            throw new DukeException(Messages.MESSAGE_INDEX_OUT_OF_BOUND);
        }
        tasklist.get(index).taskDone();
        TextUi.printDone(tasklist, index);
        int taskCounter = tasklist.size();
        TextUi.printRemaining(taskCounter);
    }
}
