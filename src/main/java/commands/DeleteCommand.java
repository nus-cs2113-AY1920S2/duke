package commands;

import exceptions.InvalidTaskException;
import task.TaskList;

public class DeleteCommand extends Command {
    public DeleteCommand (String description, TaskList tasks) {
        super(description, tasks);
    }

    @Override
    public void execute() throws InvalidTaskException {
        tasks.deleteTask(description);
    }
}