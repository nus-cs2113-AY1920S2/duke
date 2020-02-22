package duke.commands;

import duke.Util.Tasklist;
import duke.taskmanager.Tasks;

public class AddCommand extends Command {
    Tasks task;
    public AddCommand(Tasks task) {
        this.task = task;
    }

    @Override
    public void execute() {
        Tasklist.add(task);
    }
}
