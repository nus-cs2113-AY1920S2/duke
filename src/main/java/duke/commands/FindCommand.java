package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.UI;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {

    protected String description;

    public FindCommand(String command, String description) {
        super(command);
        this.description = description;
    }

    @Override
    public void execute(TaskList tasklist, UI ui, Storage storage) {
        List<Task> foundList = new ArrayList<Task>();
        for (Task task : tasklist.getTaskList()) {
            if (task.getDescription().contains(this.description)) {
                foundList.add(task);
            }
        }
        if (foundList.isEmpty()) {
            ui.displayUnableToFindTaskMessage();
        } else {
            ui.displayFoundTaskMessage(foundList);
        }
    }
}
