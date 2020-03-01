package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.ui.UI;

public class DeleteCommand extends Command {

    protected String description;

    public DeleteCommand(String command, String description) {
        super(command);
        this.description = description;
    }

    @Override
    public void execute(TaskList tasklist, UI ui, Storage storage) {
        int taskIndex = Integer.parseInt(this.description) - 1;
        if (taskIndex >= tasklist.getLength()) throw new IndexOutOfBoundsException();
        Task deleteTask = tasklist.getTaskList().get(taskIndex);
        tasklist.removeTask(deleteTask);
        storage.rewriteFile();
        ui.displayDeleteTaskMessage(deleteTask);
    }
}
