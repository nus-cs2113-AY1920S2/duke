package duke.command;

import duke.TaskList;
import duke.exception.EmptyListException;
import duke.Storage;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Storage storage ) throws EmptyListException {
        tasks.listTasks();
    }
}
