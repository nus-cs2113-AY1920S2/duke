package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Storage;

import java.util.ArrayList;

public class FindCommand implements Command {
    private String searchTerm;

    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task i: taskList.getList()) {
            if (i.getTaskDescription().toLowerCase().contains(searchTerm.toLowerCase())) {
                foundTasks.add(i);
            }
        }
        ui.showFind(foundTasks);
    }
}
