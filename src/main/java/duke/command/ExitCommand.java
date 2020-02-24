package duke.command;

import duke.exception.DukeException;
import duke.exception.DukeWritingException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Storage;

public class ExitCommand implements Command{
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeWritingException {
        storage.save(taskList);
        ui.showGoodbyeMessage();
    }
}
