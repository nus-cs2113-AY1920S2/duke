package duke.command;

import duke.common.DukeException;
import duke.tasklist.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Deals with user Command.
 * Abstract class since there can not be general command.
 */
public abstract class Command {
    public Command(){
    }

    /**
     * Deals with iteration between tasks, ui and storage.
     *
     * @param tasks Stores all tasks.
     * @param ui Deals with user interface.
     * @param storage Deals with back up file.
     * @throws IOException If cannot find back up file in the hard disk.
     * @throws DukeException Deals with other exception.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException;

    public Boolean isExit() {
        return false;
    }

}
