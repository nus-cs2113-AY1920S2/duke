package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.exceptions.TaskDescriptionException;
import duke.exceptions.TaskException;
import duke.exceptions.EventDateException;
import duke.exceptions.TaskNumberException;

/**
 * Command to add Event tasks.
 */
public class EventCommand extends ExecuteCommand {

    /**
     * Constructor for Event Command.
     * @param userData String of user input.
     */
    public EventCommand(String userData) {
        this.userData = userData;
        this.toExit = false;
    }

    /**
     * Parse user input and adds Event tasks in TaskList object.
     * Stores existing TaskList object into output file.
     * @param tasks TaskList class object that handles all ArrayList of Tasks commands.
     * @param storage Storage class object that loads and saves data.
     * @throws TaskException If task description is missing.
     * @throws EventDateException If date of event tasks is missing.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws TaskException, EventDateException {
        if (!userData.trim().toLowerCase().equals("event")) {
            int indexOfAt = userData.indexOf("/at");
            if (indexOfAt == -1) {
                throw new EventDateException("Please specify a date for event by using \'at\'!");
            }
            String[] newData = userData.split(" /at ");
            if (newData.length == 1) {
                throw new EventDateException("Please specify a date after \'/at\'!");
            }
            tasks.eventTask(newData[0], newData[1]);
            storage.saveData(tasks);
        } else {
            throw new TaskException("Add a task behind 'event', task cannot be left empty.");
        }
    }
}
