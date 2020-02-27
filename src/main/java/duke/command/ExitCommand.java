package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class ExitCommand extends Command{
    public ExitCommand() {

    }

    @Override
    public Boolean isExit() {
        return true;
    }

    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        ui.showGoodByeMessage();
    }
}
