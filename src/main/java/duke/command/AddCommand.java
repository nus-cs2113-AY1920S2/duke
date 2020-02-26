package duke.command;

import duke.taskList.TaskList;
import duke.storage.Storage;
import duke.taskList.task.Task;
import duke.ui.Ui;

import java.io.IOException;

public class AddCommand extends Command{
    private Task newtask;
    public AddCommand(Task newtask) {
        this.newtask = newtask;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.addTask(newtask);
        ui.showAddTaskMessage(tasks);
        storage.write(tasks);
    }
}
