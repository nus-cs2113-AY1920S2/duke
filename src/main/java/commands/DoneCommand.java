package commands;

import exceptions.CompletedTaskException;
import exceptions.IndexFormatException;
import exceptions.MissingIndexException;
import exceptions.OutOfRangeException;
import storage.Storage;
import tasks.Task;
import java.util.ArrayList;

/**
 * Represents all the logic used in done command
 */
public class DoneCommand extends TaskList{

    protected Storage storage;
    protected ErrorMessages errorMessages;
    protected ArrayList<Task> tasks;
    protected static final int LENGTH_OF_COMMAND = 4;

    public DoneCommand() {
        storage = new Storage();
        errorMessages = new ErrorMessages();
        tasks = new ArrayList<Task>();
    }

    /**
     * Checks exceptions for done command
     * Marks tasks as done
     * @param input String input by user
     * @param numericalIndex String index by user
     * @param isNumber boolean to check if index is number
     * @param tasks task lists to store tasks
     * @throws IndexFormatException if index is not a number
     * @throws OutOfRangeException if index <= 0 or index > size of task list
     * @throws CompletedTaskException if task has already been completed
     * @throws MissingIndexException if index is missing
     */
    public void checkExceptions(String input, String numericalIndex, boolean isNumber, ArrayList<Task> tasks) throws
            IndexFormatException, OutOfRangeException, CompletedTaskException, MissingIndexException {
        if (numericalIndex.length() == 0) {
            errorMessages.doneErrorMessage();
            throw new MissingIndexException();
        } else if (!isNumber) {
            errorMessages.doneErrorMessage();
            throw new IndexFormatException();
        } else if (isOutOfRange(numericalIndex, tasks)) {
            errorMessages.doneErrorMessage();
            throw new OutOfRangeException();
        } else if (isCompleted(numericalIndex, tasks)){
            throw new CompletedTaskException();
        } else {
            int index = Integer.parseInt(numericalIndex) - 1;
            Task completedTask = tasks.get(index);
            completedTask.markAsDone();
            tasks.set(index, completedTask);
            System.out.println(" Duke has marked the following tasks as done:");
            System.out.println("  " + completedTask);
            storage.save(tasks);
        }
    }

    /**
     * Marks task as completed
     * @param input String input by user
     * @throws IndexFormatException if index is not a number
     * @throws OutOfRangeException if index <= 0 or index > size of tasks list
     * @throws CompletedTaskException if task has already been completed
     */
    public void markAsDone(String input) throws IndexFormatException, OutOfRangeException, CompletedTaskException,
            MissingIndexException {
        String numericalIndex = getStringIndex(input, LENGTH_OF_COMMAND);
        boolean isNumber = isNumeric(numericalIndex);
        tasks = storage.loadTasks();
        checkExceptions(input, numericalIndex, isNumber, tasks);
    }
}
