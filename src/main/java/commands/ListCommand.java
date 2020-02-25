package commands;

import task.TaskList;

/**
 * Represents the command to list all existing tasks.
 */
public class ListCommand extends Command {

    public ListCommand (String description, TaskList tasks) {
        super(description, tasks);
    }

    /**
     * Lists all tasks in the task list.
     */
    @Override
    public void execute() {
        tasks.listTasks();
    }
}
