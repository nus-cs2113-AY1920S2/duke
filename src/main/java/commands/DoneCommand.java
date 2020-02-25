package commands;

import task.TaskList;

/**
 * Represents the command to mark a task as done.
 */
public class DoneCommand extends Command {
    public DoneCommand (String description, TaskList tasks) {
        super(description, tasks);
    }

    /**
     * Sets a task's completion status to true.
     */
    @Override
    public void execute() {
        tasks.markDone(description);
    }
}