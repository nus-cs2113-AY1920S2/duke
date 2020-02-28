package commands;

import java.util.ArrayList;
import exceptions.EmptyStringException;
import exceptions.MissingDescriptionsException;
import exceptions.MissingDetailsException;
import exceptions.MissingParameterException;
import tasks.Deadline;
import tasks.Task;
import storage.Storage;

/**
 * Represents the logic behind deadline commands
 */
public class DeadlineCommand extends TaskList{

    protected ErrorMessages errorMessages;
    protected ArrayList<Task> tasks;
    protected Storage storage;
    protected static final int LENGTH_OF_COMMAND = 8;
    protected static final String PARAMETER = "/by";

    public DeadlineCommand() {
        tasks = new ArrayList<Task>();
        errorMessages = new ErrorMessages();
        storage = new Storage();
    }

    /**
     * Adds deadline to task list
     * @param input String input by user
     * @param lengthOfCommand length of command used by user
     */
    public void addDeadlineToList(String input, int lengthOfCommand) {
        tasks = storage.loadTasks();
        String description = getDescription(input, lengthOfCommand);
        String details = getDetails(input, lengthOfCommand);
        Deadline newDeadline = new Deadline(description, details);
        tasks.add(newDeadline);
        System.out.println(" Got it!I've added this task:");
        System.out.println(" " + newDeadline);
        System.out.println(" There are currently " + tasks.size() + " task(s) being queued");
        storage.save(tasks);
    }

    /**
     * Adds deadline task to the task list
     * @param input String input by user
     * @throws EmptyStringException if all fields of deadline tasks are empty
     * @throws MissingParameterException if /by is missing
     * @throws MissingDescriptionsException if description of deadline task is missing
     * @throws MissingDetailsException if details of deadline is missing
     */
    public void addDeadline(String input) throws EmptyStringException, MissingParameterException,
            MissingDescriptionsException, MissingDetailsException {
        String removeTrailingSpaces = input.trim();
        if (removeTrailingSpaces.length() == LENGTH_OF_COMMAND) {
            errorMessages.deadlineErrorMessage();
            throw new EmptyStringException();
        } else if (!removeTrailingSpaces.contains(PARAMETER)) {
            errorMessages.deadlineErrorMessage();
            throw new MissingParameterException();
        } else if (isEmptyDescription(input, LENGTH_OF_COMMAND)) {
            errorMessages.deadlineErrorMessage();
            throw new MissingDescriptionsException();
        } else if (isEmptyDetails(input, LENGTH_OF_COMMAND)) {
            errorMessages.deadlineErrorMessage();
            throw new MissingDetailsException();
        } else {
            addDeadlineToList(input, LENGTH_OF_COMMAND);
        }
    }
}
