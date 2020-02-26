//package duke.command;
package duke.command;
import duke.common.DukeException;
import duke.taskList.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.IOException;

public class Command {
    public Command(){

    }

    public void execute (TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {

    }

    public Boolean isExit() {
        return false;
    }

}
