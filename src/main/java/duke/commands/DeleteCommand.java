package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.exceptions.DeadlineDateException;
import duke.exceptions.DeadlineTaskException;
import duke.exceptions.TaskDescriptionException;
import duke.exceptions.TaskNumberException;

/**
 * Command to delete task from tasks in TaskList.
 */
public class DeleteCommand extends ExecuteCommand {

    /**
     * Constructor for Delete Command.
     * @param userData String of user input.
     */
    public DeleteCommand(String userData) {
        this.userData = userData;
        this.toExit = false;
    }

    /**
     * Parse user input and deletes tasks in  the TaskList object.
     * Stores existing TaskList object into output file.
     * @param tasks TaskList class object that handles all ArrayList of Tasks commands.
     * @param storage Storage class object that loads and saves data.
     * @throws TaskNumberException If task number from user input does not exist in list.
     * @throws TaskDescriptionException If task number of task to be deleted is missing.
     */
    public void execute(TaskList tasks, Storage storage) throws TaskNumberException, TaskDescriptionException {
        if (!userData.trim().toLowerCase().equals("delete")) {
            String[] newData = userData.split(" ");
            int index = Integer.parseInt(newData[1]);
            int sizeOfList = tasks.sizeOfList();
            if (index <= sizeOfList) {
                tasks.deleteCommand(index);
                storage.saveData(tasks);
            } else {
                throw new TaskNumberException("Task number does not exist in the list.");
            }
        } else {
            throw new TaskDescriptionException("Add a task number after \'done\' to mark task as done.");
        }
    }
}
