package duke.command;

import duke.common.DukeException;
import duke.tasklist.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Deals with command related to mark tasks as done.
 */
public class DoneCommand extends Command {
    private int doneCount;

    public DoneCommand(int doneCount) {
        /** The array index will be actual count minus one */
        this.doneCount = doneCount - 1;
    }

    /**
     * Marks a task as done, asks ui to show task marked as done, and updates back up file.
     *
     * @param tasks stores all tasks.
     * @param ui deals with user interface.
     * @param storage deals with back up file.
     * @throws IOException if cannot find back up file in the hard disk.
     * @throws DukeException if the intended task to mark as done is invalid e.g. not exist.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        if (doneCount + 1 > tasks.size()) {
            throw new DukeException("\tThere is no task " + (doneCount + 1) + ". Please reconsider the index.");
        }
        tasks.getATask(this.doneCount).markAsDone();
        ui.showMarkAsDoneMessage(tasks,this.doneCount);
        storage.write(tasks);
    }
}
