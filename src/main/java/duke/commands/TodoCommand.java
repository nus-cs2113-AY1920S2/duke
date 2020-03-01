package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.exceptions.DeadlineDateException;
import duke.exceptions.DeadlineTaskException;
import duke.exceptions.TaskException;

/**
 * Command to add Todo tasks.
 */
public class TodoCommand extends ExecuteCommand {

    /**
     * Constructor for Todo Command.
     * @param userData String of user input.
     */
    public TodoCommand(String userData) {
        this.userData = userData;
        this.toExit = false;
    }

    /**
     * Parse user input and adds todo tasks into TaskList object.
     * Stores existing TaskList object into output file.
     * @param tasks TaskList class object that handles all ArrayList of Tasks commands.
     * @param storage Storage class object that loads and saves data.
     * @throws TaskException If description of todo tasks is missing.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws TaskException {
        if (!userData.trim().toLowerCase().equals("todo")) {
            String[] removeTodo = userData.split(" ", 2);
            tasks.todoTask(removeTodo[1]);
            storage.saveData(tasks);
        } else {
            throw new TaskException("Add a task behind 'todo', task cannot be left empty.");
        }
    }
}
