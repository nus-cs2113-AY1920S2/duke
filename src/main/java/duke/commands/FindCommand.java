package duke.commands;

import duke.TaskList;
import duke.Storage;
import duke.exceptions.TaskException;

/**
 * Command to find tasks.
 */
public class FindCommand extends ExecuteCommand {

    /**
     * Constructor for Find Command.
     * @param userData String of user input.
     */
    public FindCommand(String userData) {
        this.userData = userData;
        this.toExit = false;
    }

    /**
     * Parse user input and adds Event tasks in TaskList object.
     * @param tasks TaskList class object that handles all ArrayList of Tasks commands.
     * @param storage Storage class object that loads and saves data.
     * @throws TaskException If task to search for is missing.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws TaskException {
        if (!userData.trim().toLowerCase().equals("find")) {
            String[] removeFind = userData.split(" ", 2);
            tasks.findCommand(removeFind[1]);
        } else {
            throw new TaskException("Add a word behind 'find' to search for relevant tasks.");
        }
    }
}