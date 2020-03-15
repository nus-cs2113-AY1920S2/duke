package duke.commands;

import duke.exceptions.MissingDateTimeException;
import duke.exceptions.MissingDescriptionException;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.taskManager.Task;
import duke.ui.DisplayUI;

import java.util.ArrayList;
import java.util.Scanner;

public class DeadlineCommand implements Command {
    private String line;

    public DeadlineCommand(String restOfInput){
        this.line = restOfInput;
    }

    public boolean execute(String function, DisplayUI ui, Storage storage, TaskList taskList, ArrayList<Task> tasks)
            throws MissingDescriptionException, MissingDateTimeException {
        String[] description = line.split("/by");
        if (description[0].equals("")) {
            throw new MissingDescriptionException("The deadline description cannot be empty! Please try again");
        }
        if (description[1].equals("")) {
            throw new MissingDateTimeException("Date/time not found! Please try again or type 'help' to check input format.");
        }
        taskList.addTask(function, description[0], description[1], tasks);
        storage.writeToFile(function, line);
        taskList.printAddedTask(ui, tasks.get(tasks.size() - 1), tasks);
        return true;
    }
}
