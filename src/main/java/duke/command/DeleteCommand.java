package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

public class DeleteCommand extends Command {

    private String[] indexInformation;

    public DeleteCommand(String[] indexInformation){
        this.commandType = CommandType.Delete;
        this.indexInformation = indexInformation;
    }

    @Override
    public void executeCommand(TaskList taskList) throws DukeException {
        taskList.deleteTask(indexInformation);
    }
}
