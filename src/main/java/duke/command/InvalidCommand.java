package duke.command;

import duke.common.DukeException;
import duke.tasklist.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Deals with command that cannot be recognized.
 */
public class InvalidCommand extends Command {

    public InvalidCommand(){
    }

    /**
     * Throws exception since there is invalid command.
     *
     * @param tasks Stores all tasks, useless here.
     * @param ui Deals with user interface, useless here.
     * @param storage Deals with back up file, useless here.
     * @throws DukeException Exists since the command is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("\t Sorry.I do not understand you.");
    }
}
