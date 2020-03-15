package duke.commands;

import duke.exceptions.MissingDateTimeException;
import duke.exceptions.MissingDescriptionException;
import duke.exceptions.MissingLocationException;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.taskManager.Task;
import duke.ui.DisplayUI;

import java.util.ArrayList;

public class ListCommand implements Command {

     public boolean execute(String function, DisplayUI ui, Storage storage, TaskList taskList, ArrayList<Task> tasks) {
         taskList.printAllTasks(ui, tasks);
         return true;
    }
}
