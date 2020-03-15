package duke.commands;

import duke.exceptions.MissingDescriptionException;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.taskManager.Task;
import duke.ui.DisplayUI;

import java.util.ArrayList;

import static duke.constants.Constants.EVENT_DESCRIPTIONS_EMPTY;
import static duke.constants.Constants.LOCATION_EMPTY;

/**
 * A event command to store new event tasks
 */
public class EventCommand implements Command {
    private String line;


    public EventCommand(String restOfInput) {
        this.line = restOfInput;
    }

    /**
     * This method will execute the command and add new event task into the tasks array
     *
     * @param function String containing "event"
     * @param ui       ui object for printing statements
     * @param storage  Storage object for accessing and modifying tasklist.txt
     * @param taskList Array of tasks stored (Not in use currently)
     * @param tasks    Array of tasks stored (In Use)
     * @return boolean true to main function
     */
    public boolean execute(String function, DisplayUI ui, Storage storage, TaskList taskList, ArrayList<Task> tasks) {
        try {
            String[] description = line.split("/at");
            if (description[0].isEmpty()) {
                throw new MissingDescriptionException(EVENT_DESCRIPTIONS_EMPTY);
            }
            if (description[1].isEmpty()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            taskList.addTask(function, description[0], description[1], tasks);
            storage.writeToFile(function, line);
            taskList.printAddedTask(ui, tasks.get(tasks.size() - 1), tasks);
        } catch (MissingDescriptionException e) {
            e.printDescr();
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printToUser(LOCATION_EMPTY);
        }
        return true;
    }
}
