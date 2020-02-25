package commands;

import task.TaskList;

public class ListCommand extends Command {

    public ListCommand (String description, TaskList tasks) {
        super(description, tasks);
    }

    @Override
    public void execute() {
        tasks.listTasks();
    }
}
