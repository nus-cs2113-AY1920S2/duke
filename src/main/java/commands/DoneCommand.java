package commands;

import exception.DukeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Command class for the Done command.
 */
public class DoneCommand extends Command {
    private int taskNumber;

    public static final String COMMAND_WORD = "done";

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.doneTask(taskNumber);
        ui.displayDoneTask(tasks, taskNumber);
        storage.saveTasks(tasks);
    }
}
