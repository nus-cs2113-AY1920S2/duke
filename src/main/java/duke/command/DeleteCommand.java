package duke.command;

import duke.data.TaskList;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    public DeleteCommand(String fullCommand, String taskType, String args) {
        super(fullCommand, taskType, args);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task taskTobeDeleted = delete(tasks);
        ui.printTask(taskTobeDeleted, tasks, "remove");
        storage.save(tasks);
    }

    public Task delete(TaskList tasks) throws DukeException {
        int taskID = Integer.parseInt(super.args);

        /* Exit if enter a wrong task id */
        boolean isWrongID = taskID > tasks.size() || taskID < 1;
        if (isWrongID) {
            throw new DukeException("Your input number is out of range!");
        }
        Task taskToBeDeleted = tasks.get(taskID - 1);
        tasks.remove(taskID - 1);
        return taskToBeDeleted;
    }

}
