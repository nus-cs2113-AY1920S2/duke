package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.exceptions.EmptyListException;

public class ListCommand extends ExecuteCommand {
    @Override
    public void execute(TaskList tasks, Storage storage ) throws EmptyListException {
        tasks.listCommand();
    }
}
