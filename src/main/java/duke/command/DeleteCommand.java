package duke.command;

import duke.common.DukeException;
import duke.tasklist.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Deals with commands related to deleting tasks.
 */
public class DeleteCommand extends Command {
    private int deleteCount;

    public DeleteCommand(int deleteCount) {
        // The array index will be actual count minus one.
        this.deleteCount = deleteCount - 1;
    }

    /**
     * Delete tasks, asks ui to show deleted task, and updates backup files.
     *
     * @param tasks stores all tasks.
     * @param ui deals with user interface.
     * @param storage deals with back up file.
     * @throws IOException if cannot find back up file in the hard disk.
     * @throws DukeException if the intended delete index is invalid e.g. not exist.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        if (deleteCount + 1 > tasks.size()) {
            throw new DukeException("\tThere is no task " + (deleteCount + 1) + ". Please reconsider the index.");
        }
        ui.showDeleteMessage(tasks.deleteTask(deleteCount),tasks);
        storage.write(tasks);
    }
}
