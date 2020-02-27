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
     * @param tasks stores all tasks.
     * @param ui deals with user interface.
     * @param storage deals with back up file.
     * @throws IOException if cannot find back up file in the hard disk.
     * @throws DukeException deals with other exception.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException;

    public Boolean isExit() {
        return false;
    }

}
