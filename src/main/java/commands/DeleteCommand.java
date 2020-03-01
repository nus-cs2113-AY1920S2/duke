package commands;

import exception.DukeException;
import storage.Storage;
import task.Event;
import task.Task;
import task.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {

    private int taskNumber;

    public static final String COMMAND_WORD = "delete";

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.displayDeleteTask(tasks, taskNumber);
        tasks.deleteTask(taskNumber);
        storage.saveTasks(tasks);
    }
}
