package commands;

import exceptions.IndexFormatException;
import exceptions.MissingIndexException;
import exceptions.OutOfRangeException;
import storage.Storage;
import tasks.Task;
import java.util.ArrayList;

/**
 * Represents the logic used in delete command
 */
public class DeleteCommand extends TaskList{

    protected Storage storage;
    protected ArrayList<Task> tasks;
    protected ErrorMessages errorMessages;

    public DeleteCommand() {
        storage = new Storage();
        tasks = new ArrayList<Task>();
        errorMessages = new ErrorMessages();
    }

    /**
     * Deletes tasks from task list
     * @param input String input by user
     * @throws IndexFormatException if index is not a number
     * @throws OutOfRangeException if index <= 0 or index >size of task list
     */
    public void deleteItem(String input) throws IndexFormatException, OutOfRangeException, MissingIndexException {
        int lengthOfCommand = 6;
        String numericalIndex = getStringIndex(input,lengthOfCommand);
        boolean isNumber = isNumeric(numericalIndex);
        tasks = storage.loadTasks();
        if (numericalIndex.length() == 0) {
            errorMessages.deleteErrorMessage();
            throw new MissingIndexException();
        } else if (!isNumber) {
            errorMessages.deleteErrorMessage();
            throw new IndexFormatException();
        } else if(isOutOfRange(numericalIndex, tasks)) {
            errorMessages.deleteErrorMessage();
            throw new OutOfRangeException();
        } else {
            int index = Integer.parseInt(numericalIndex) - 1;
            Task deletedTask = tasks.get(index);
            int length = tasks.size() - 1;
            tasks.remove(index);
            System.out.println(" Got it!I've removed this task: ");
            System.out.println(" " + deletedTask);
            System.out.println(" There are currently " + length + " task(s) being queued");
            storage.save(tasks);
        }
    }
}
