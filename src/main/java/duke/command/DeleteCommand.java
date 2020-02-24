package duke.command;

import duke.exception.DukeTaskIdInvalidException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Storage;

import java.util.ArrayList;

public class DeleteCommand implements Command{
    int taskId;

    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeTaskIdInvalidException {
        try {
            ArrayList<Task> list = taskList.getList();
            Task deletedTask = list.get(taskId);
            list.remove(taskId);
            ui.showDeleteTaskSuccessfulPrompt(taskList, deletedTask);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeTaskIdInvalidException();
        }
    }
}
