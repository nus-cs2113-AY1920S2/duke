package duke.commands;

import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.taskManager.Task;
import duke.ui.DisplayUI;

import java.util.ArrayList;

public class HelpCommand implements Command {

    public boolean execute(String function, DisplayUI ui, Storage storage, TaskList taskList, ArrayList<Task> tasks) {
        ui.showFunctionList();
        return true;
    }
}
