package duke.commands;

import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.taskManager.Task;
import duke.ui.DisplayUI;

import java.util.ArrayList;

/**
 * This command object creates a new todo task and store into array list
 */
public class TodoCommand implements Command {
    private String descriptions;

    public TodoCommand(String restOfInput) {
        this.descriptions = restOfInput;
    }

    /**
     * execute will create a new todo task to be stored into the task array
     *
     * @param function Containing a string "todo"
     * @param ui       ui object for printing out statements
     * @param storage  Storage object to access and modify TaskList.txt
     * @param taskList Array list of tasks (Not used at the moment)
     * @param tasks    Array list of tasks (From duke main)
     * @return
     */
    public boolean execute(String function, DisplayUI ui, Storage storage, TaskList taskList, ArrayList<Task> tasks) {
        taskList.addTask(descriptions, tasks);
        storage.writeToFile(function, descriptions);
        taskList.printAddedTask(ui, tasks.get(tasks.size() - 1), tasks);
        return true;
    }
}
