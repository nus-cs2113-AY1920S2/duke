package duke.command;

import duke.TaskList;
import duke.exception.MissingTaskNumberDescriptionException;
import duke.exception.MissingTaskNumberException;
import duke.Storage;

/**
 * Command that deletes task from existing tasks.
 */
public class DeleteCommand extends Command {

    private final String DELETE_COMMAND = "delete";

    /**
     * Default constructor for Delete Command class.
     * @param userInput String containing full input from User
     */
    public DeleteCommand(String userInput) {
        this.userInput = userInput;
        this.isExit = false;
    }

    /**
     * Processes the input and deletes tasks in the TaskList object.
     * Saves the TaskList object into file.
     * @param tasks TaskList class object which handles operation involving ArrayList of Tasks
     * @param storage Storage class object which manages storing and loading of data
     * @throws MissingTaskNumberException If specified task number does not exist on the list
     * @throws MissingTaskNumberDescriptionException If task number to be deleted is not specified
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws MissingTaskNumberException, MissingTaskNumberDescriptionException {
        if (!userInput.trim().equals(DELETE_COMMAND)) {
            String[] words = userInput.split(" ");
            int taskNum = Integer.parseInt(words[1]);
            if(taskNum <= tasks.getListSize()) {
                tasks.deleteTask(taskNum);
                storage.save(tasks);
            } else {
                throw new MissingTaskNumberException("This task number does not exist on the list!");
            }
        } else {
            throw new MissingTaskNumberDescriptionException("Please add a task number to \'delete\' to delete a task!");
        }
    }
}
