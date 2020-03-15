package duke.commands;

import duke.exceptions.MissingDescriptionException;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.taskManager.Task;
import duke.ui.DisplayUI;

import java.util.ArrayList;

import static duke.constants.Constants.DEADLINE_DESCRIPTIONS_EMPTY;
import static duke.constants.Constants.DATETIME_EMPTY;

/**
 * A deadline command to store new deadline tasks
 */
public class DeadlineCommand implements Command {
    private String line;

    public DeadlineCommand(String restOfInput) {
        this.line = restOfInput;
    }

    /**
     * This method will execute the command and add new deadline task into the tasks array
     *
     * @param function String containing "deadline"
     * @param ui       ui object for printing statements
     * @param storage  Storage object for accessing and modifying tasklist.txt
     * @param taskList Array of tasks stored (Not in use currently)
     * @param tasks    Array of tasks stored (In Use)
     * @return boolean true to main function
     */
    public boolean execute(String function, DisplayUI ui, Storage storage, TaskList taskList, ArrayList<Task> tasks) {
        try {
            String[] description = line.split("/by");
            if (description[0].isEmpty()) {
                throw new MissingDescriptionException(DEADLINE_DESCRIPTIONS_EMPTY);
            }
            if (description[1] == null) {
                throw new ArrayIndexOutOfBoundsException();
            }
            taskList.addTask(function, description[0], description[1], tasks);
            storage.writeToFile(function, line);
            taskList.printAddedTask(ui, tasks.get(tasks.size() - 1), tasks);
        } catch (MissingDescriptionException e) {
            e.printDescr();
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printToUser(DATETIME_EMPTY);
        }
        return true;
    }
}
