package commands;

import exception.DukeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class DoneCommand extends Command {
    private int taskNumber;

    public static final String COMMAND_WORD = "done";

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.doneTask(taskNumber);
        ui.displayDoneTask(tasks, taskNumber);
        storage.saveTasks(tasks);
    }
}
