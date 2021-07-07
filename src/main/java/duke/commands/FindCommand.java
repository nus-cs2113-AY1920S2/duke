package duke.commands;

import duke.task.TaskList;

public class FindCommand extends Command {
    public FindCommand (String description, TaskList tasks) {
        super(description, tasks);
    }

    @Override
    public void execute() {
        tasks.findTasks(description);
    }
}