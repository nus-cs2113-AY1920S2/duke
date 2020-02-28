package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.ui.UI;

public class doneCommand extends Command {

    protected String description;

    public doneCommand(String command, String description) {
        super(command);
        this.description = description;
    }

    @Override
    public void execute(TaskList tasklist, UI ui, Storage storage) {
        int taskIndex = Integer.parseInt(this.description) - 1;
        if (taskIndex >= tasklist.getLength()) throw new IndexOutOfBoundsException();
        Task doneTask = tasklist.getTaskList().get(taskIndex);
        doneTask.setDone(true);
        ui.displayDoneTaskMessage(doneTask);
    }
}
