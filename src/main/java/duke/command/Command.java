package duke.command;

import duke.exception.DukeException;
import duke.exception.DukeWritingException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Storage;

public interface Command {
    boolean isExit();

    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, DukeWritingException;
}
