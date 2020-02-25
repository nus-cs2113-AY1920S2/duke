package commands;

import task.TaskList;

public class DoneCommand extends Command {
    public DoneCommand (String description, TaskList tasks) {
        super(description, tasks);
    }

    @Override
    public void execute() {
        tasks.markDone(description);
    }
}