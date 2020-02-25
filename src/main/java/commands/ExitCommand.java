package commands;

import task.TaskList;

public class ExitCommand extends Command {
    public ExitCommand (String description, TaskList tasks) {
        super(description, tasks);
    }

    @Override
    public void execute() {
        this.isExit = true;
    }
}