package duke.commands;

import duke.exceptions.MissingDescriptionException;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.taskManager.Task;
import duke.ui.DisplayUI;

import java.util.ArrayList;
import java.util.Scanner;

public class TodoCommand implements Command {
    private String descriptions;

    public TodoCommand(String restOfInput){
        this.descriptions = restOfInput;
    }

    /**
     * execute will create a new todo task to be stored into the task array
     * @param function Containing a string "todo"
     * @param ui ui object for printing out statements
     * @param storage Storage object to access and modify TaskList.txt
     * @param taskList Array list of tasks (Not used at the moment)
     * @param tasks Array list of tasks (From duke main)
     * @return
     * @throws MissingDescriptionException
     */
    public boolean execute(String function, DisplayUI ui, Storage storage, TaskList taskList, ArrayList<Task> tasks)
            throws MissingDescriptionException {
        if (descriptions.equals("")) {
            throw new MissingDescriptionException("The todo description is empty. Please try again or type 'help' to check input formats");
        }
        taskList.addTask(descriptions, tasks);
        storage.writeToFile(function, descriptions);
        taskList.printAddedTask(ui, tasks.get(tasks.size() - 1), tasks);
        return true;
    }
}
