package commands;

import exceptions.InvalidTaskException;
import task.TaskList;

/**
 * Represents the command to delete a task from the list.
 */
public class DeleteCommand extends Command {
    public DeleteCommand (String description, TaskList tasks) {
        super(description, tasks);
    }

    /**
     * Removes the task from the task list based on the index given.
     * @throws InvalidTaskException If task index is out of bounds.
     */
    @Override
    public void execute() throws InvalidTaskException {
        tasks.deleteTask(description);
    }
}