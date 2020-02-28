package commands;

import exceptions.MissingDescriptionsException;
import java.util.ArrayList;
import tasks.Task;
import storage.Storage;

/**
 * Represents the logic behind todo command
 */
public class ToDoCommand {

    protected ErrorMessages errorMessages;
    protected ArrayList<Task> tasks;
    protected Storage storage;
    protected static final int LENGTH_OF_COMMAND = 4;

    public ToDoCommand() {
        tasks = new ArrayList<Task>();
        errorMessages = new ErrorMessages();
        storage = new Storage();
    }

    /**
     * Adds a toDo task to the task list
     * @param input String input by user
     * @throws MissingDescriptionsException if ToDo task is missing a description
     */
    public void addToDo(String input) throws MissingDescriptionsException {
        String removeTrailingSpaces = input.trim();
        tasks = storage.loadTasks();
        if (removeTrailingSpaces.length() == LENGTH_OF_COMMAND) {
            errorMessages.toDoErrorMessage();
            throw new MissingDescriptionsException();
        } else {
            String description = removeTrailingSpaces.substring(5, input.length());
            tasks.ToDo newToDo = new tasks.ToDo(description);
            tasks.add(newToDo);
            System.out.println(" Got it! I have added the following tasks: ");
            System.out.println("    " + newToDo);
            System.out.println("There are currently " + tasks.size() + " task(s) queued");
            storage.save(tasks);
        }
    }
}
