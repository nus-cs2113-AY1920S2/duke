package duke.command;

import duke.exception.MissingTaskException;
import duke.exception.MissingEventDateException;
import duke.TaskList;
import duke.Storage;

/**
 * Command to add Event tasks into Duke.
 */
public class EventCommand extends Command {

    private final String EVENT_COMMAND = "event";

    /**
     * Default constructor for Event Command class.
     * @param userInput String containing full input from User
     */
    public EventCommand(String userInput) {
        this.userInput = userInput;
        this.isExit = false;
    }

    /**
     * Processes input and adds Event task into TaskList object.
     * Saves current TaskList object into a file.
     * @param tasks TaskList class object which handles operation involving ArrayList of Tasks
     * @param storage Storage class object which manages storing and loading of data
     * @throws MissingTaskException If task description is not specified
     * @throws MissingEventDateException If specified task does not have a date/time of occurring
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws MissingTaskException, MissingEventDateException {
        if (!userInput.trim().equals(EVENT_COMMAND)) {
            int indexOfAt = userInput.indexOf("/at");
            if (indexOfAt == -1) {
                throw new MissingEventDateException("Please specify the date for event using \'at\'!");
            }
            String eventTask = userInput.substring(EVENT_COMMAND.length() + 1, indexOfAt - 1);
            String atDate = userInput.substring(indexOfAt + "/at".length() + 1);
            tasks.addEvent(eventTask, atDate);
            storage.save(tasks);
        } else {
            throw new MissingTaskException("Event tasks cannot be empty!");
        }
    }
}
