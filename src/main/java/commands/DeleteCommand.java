package commands;

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
     */
    @Override
    public void execute() {
        tasks.deleteTask(description);
    }
}