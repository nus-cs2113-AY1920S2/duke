package duke.commands;

import duke.exceptions.MissingDescriptionException;
import duke.exceptions.MissingLocationException;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.taskManager.Task;
import duke.ui.DisplayUI;

import java.util.ArrayList;
import java.util.Scanner;

public class EventCommand implements Command {
    private String line;

    public EventCommand(String restOfInput){
        this.line = restOfInput;
    }

    public boolean execute(String function, DisplayUI ui, Storage storage, TaskList taskList, ArrayList<Task> tasks)
            throws MissingDescriptionException, MissingLocationException {
        String[] description = line.split("/at");
        if (description[0].equals("")) {
            throw new MissingDescriptionException("The event description cannot be empty! PLease try again");
        }
        if (description[1].equals(null)) {
            throw new MissingLocationException("Location not found! Please try again or type 'help' to check input format.");
        }
        taskList.addTask(function, description[0], description[1], tasks);
        storage.writeToFile(function, line);
        taskList.printAddedTask(ui, tasks.get(tasks.size() - 1), tasks);
        return true;
    }
}
