package duke.command;

import duke.exception.MissingTaskException;
import duke.TaskList;
import duke.Storage;

/**
 * Command to add Todo tasks into Duke.
 */
public class TodoCommand extends Command {

    private final String TODO_COMMAND = "todo";

    /**
     * Default constructor for Todo Command class.
     * @param userInput String containing full input from User
     */
    public TodoCommand(String userInput) {
        this.userInput = userInput;
        this.isExit = false;
    }

    /**
     * Processes input and adds Todo task into TaskList object.
     * Saves current TaskList into a file.
     * @param tasks TaskList class object which handles operation involving ArrayList of Tasks
     * @param storage Storage class object which manages storing and loading of data
     * @throws MissingTaskException If description of Todo task is not supplied by User
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws MissingTaskException {
        if (!userInput.trim().equals(TODO_COMMAND)) {
            String todoTask = userInput.substring(TODO_COMMAND.length() + 1);
            tasks.addTodo(todoTask);
            storage.save(tasks);
        } else {
            throw new MissingTaskException("Todo tasks cannot be empty!");
        }

    }




}
