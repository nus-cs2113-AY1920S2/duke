package commands;

import exceptions.EmptyStringException;
import exceptions.MissingDescriptionsException;
import exceptions.MissingDetailsException;
import exceptions.MissingParameterException;
import storage.Storage;
import tasks.Event;
import tasks.Task;
import java.util.ArrayList;

/**
 * Represents the logic behind event command
 */
public class EventCommand extends TaskList {

    protected ArrayList<Task> tasks;
    protected Storage storage;
    protected ErrorMessages errorMessages;
    protected static final int LENGTH_OF_COMMAND = 5;
    protected static final String PARAMETER = "/at";

    public EventCommand() {
        tasks = new ArrayList<Task>();
        storage = new Storage();
        errorMessages = new ErrorMessages();
    }

    /**
     * Adds Event to task list
     * @param input String user input
     * @param lengthOfCommand length of command that user input
     */
    public void addEventToList(String input, int lengthOfCommand) {
        tasks = storage.loadTasks();
        String description = getDescription(input, lengthOfCommand);
        String details = getDetails(input, lengthOfCommand);
        Event newEvent = new Event(description, details);
        tasks.add(newEvent);
        System.out.println(" Got it!I've added this task:");
        System.out.println(" " + newEvent);
        System.out.println(" There are currently " + tasks.size() + " task(s) being queued");
        storage.save(tasks);
    }

    /**
     * Adds an event task to the task list
     * Checks for exceptions when adding event to task list
     * @param input String input by user
     * @throws EmptyStringException if all fields are missing
     * @throws MissingParameterException if /at is missing
     * @throws MissingDescriptionsException if descriptions of event task is missing
     * @throws MissingDetailsException if event details are missing
     */
    public void addEvent(String input) throws EmptyStringException, MissingParameterException,
            MissingDescriptionsException, MissingDetailsException {
        String removeTrailingSpaces = input.trim();
        if (removeTrailingSpaces.length() == LENGTH_OF_COMMAND) {
            errorMessages.eventErrorMessage();
            throw new EmptyStringException();
        } else if (!removeTrailingSpaces.contains(PARAMETER)) {
            errorMessages.eventErrorMessage();
            throw new MissingParameterException();
        } else if (isEmptyDescription(input, LENGTH_OF_COMMAND)) {
            errorMessages.eventErrorMessage();
            throw new MissingDescriptionsException();
        } else if (isEmptyDetails(input, LENGTH_OF_COMMAND)) {
            errorMessages.eventErrorMessage();
            throw new MissingDetailsException();
        } else {
            addEventToList(input, LENGTH_OF_COMMAND);
        }
    }
}
