package duke.command;

import duke.exception.DukeException;
import duke.exception.DukeTaskIdInvalidException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Storage;

public class DoneCommand implements Command{
    int taskId;

    public DoneCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeTaskIdInvalidException {
        try {
            taskList.getList().get(taskId).markAsDone();
            ui.showMarkAsDoneSuccessfulPrompt(taskList.getList().get(taskId));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeTaskIdInvalidException();
        }
    }
}
