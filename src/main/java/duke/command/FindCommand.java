package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.exception.MissingTaskException;

/**
 * Command for finding tasks in Duke.
 */
public class FindCommand extends Command {

    private final String FIND_COMMAND = "find";

    /**
     * Default constructor for Find Command class.
     * @param userInput String containing full input from User.
     */
    public FindCommand(String userInput) {
        this.userInput = userInput;
        this.isExit = false;
    }

    /**
     * Processes input and finds the tasks in TaskList object based on input.
     * @param tasks TaskList class object which handles operation involving ArrayList of Tasks
     * @param storage Storage class object which manages storing and loading of data
     * @throws MissingTaskException If there is no keyword supplied by user to search for
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws MissingTaskException {
        if (!userInput.trim().equals(FIND_COMMAND)) {
            tasks.findTask(userInput.substring(FIND_COMMAND.length() + 1));
        } else {
            throw new MissingTaskException("Please specify a word or phrase to search for!");
        }

    }


}
