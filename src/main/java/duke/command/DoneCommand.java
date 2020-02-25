package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

public class DoneCommand extends Command {

    private String[] indexInformation;

    public DoneCommand(String[] indexInformation) {
        this.commandType = CommandType.Done;
        this.indexInformation = indexInformation;
    }

    @Override
    public void executeCommand(TaskList taskList) throws DukeException {
        taskList.markTaskAsDone(indexInformation);
    }
}
