package duke.command;

import duke.common.DukeException;
import duke.taskList.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class InvalidCommand extends Command {

    public InvalidCommand(){

    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("\t Sorry.I do not understand you.");
    }
}
