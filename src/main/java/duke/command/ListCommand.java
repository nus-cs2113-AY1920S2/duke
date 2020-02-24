package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

public class ListCommand extends Command {

    private boolean isOneWordCommand;

    public ListCommand(boolean isOneWordCommand) {
        this.commandType = CommandType.List;
        this.isOneWordCommand = isOneWordCommand;
    }

    @Override
    public void executeCommand(TaskList taskList) throws DukeException {
        taskList.listTasks(isOneWordCommand);
    }
}
