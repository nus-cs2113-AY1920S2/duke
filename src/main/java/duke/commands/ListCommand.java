package duke.commands;

import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.exception.DukeException;

import java.text.ParseException;

public class ListCommand extends Command {
    public ListCommand() {
        super();
    }

    public void execute(TaskList taskList, Ui uiInput, Storage storage) throws DukeException, ParseException {
        taskList.listTask();
    }
    public boolean isExit() {
        return false;
    }
}
