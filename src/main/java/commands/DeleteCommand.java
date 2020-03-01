package commands;

import exception.DukeException;
import storage.Storage;
import task.Event;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Command class for the Delete command.
 */
public class DeleteCommand extends Command {

    private int taskNumber;

    public static final String COMMAND_WORD = "delete";

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.displayDeleteTask(tasks, taskNumber);
        tasks.deleteTask(taskNumber);
        storage.saveTasks(tasks);
    }
}
