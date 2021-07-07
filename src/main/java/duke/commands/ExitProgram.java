package duke.commands;

import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.exception.DukeException;

import java.text.ParseException;

public class ExitProgram extends Command {
    public ExitProgram() {
        super();
    }

    public void execute(TaskList taskList, Ui uiInput, Storage storage) throws DukeException, ParseException {
        uiInput.exit();
    }
    public boolean isExit() {
        return true;
    }
}
