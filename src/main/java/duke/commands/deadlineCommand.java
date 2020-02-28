package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Deadline;
import duke.ui.UI;

public class deadlineCommand extends Command {

    protected String by;
    protected String description;

    public deadlineCommand(String command, String description, String by) {
        super(command);
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasklist, UI ui, Storage storage) {
        Deadline deadline = new Deadline(this.description, this.by);
        tasklist.addTask(deadline);
        ui.displayAddDeadlineMessage(deadline, tasklist.getTaskList());
    }
}
