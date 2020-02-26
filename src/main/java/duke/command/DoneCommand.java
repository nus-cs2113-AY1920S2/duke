package duke.command;

import duke.TaskList;
import duke.exception.MissingTaskNumberDescriptionException;
import duke.exception.MissingTaskNumberException;
import duke.Storage;

/**
 * Command to mark tasks as done.
 */
public class DoneCommand extends Command {

    private final String DONE_COMMAND = "done";

    /**
     * Default constructor for Done Command class.
     * @param userInput String containing full input from User.
     */
    public DoneCommand(String userInput) {
        this.userInput = userInput;
        this.isExit = false;
    }

    /**
     * Processes the input and marks tasks on TaskList object as done.
     * Saves the current TaskList object into a file.
     * @param tasks TaskList class object which handles operation involving ArrayList of Tasks
     * @param storage Storage class object which manages storing and loading of data
     * @throws MissingTaskNumberException If specified task number does not exist on the list
     * @throws MissingTaskNumberDescriptionException If task number to be marked as done is not specified
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws MissingTaskNumberException, MissingTaskNumberDescriptionException {
        if (!userInput.trim().equals(DONE_COMMAND)) {
            String[] words = userInput.split(" ");
            int taskNum = Integer.parseInt(words[1]);
            int sizeOfArray = tasks.getListSize();
            if (taskNum <= sizeOfArray) {
                tasks.markDone(taskNum);
                storage.save(tasks);
            } else {
                throw new MissingTaskNumberException("This task number does not exist on the list!");
            }
        } else {
            throw new MissingTaskNumberDescriptionException("Please add a task number to \'done\' to mark task as done!");
        }

    }


}
