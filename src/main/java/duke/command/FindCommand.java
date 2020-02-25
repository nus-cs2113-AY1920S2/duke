package duke.command;

import duke.exception.DukeException;
import duke.exception.ExceptionType;
import duke.task.TaskList;

public class FindCommand extends Command {
    private boolean isCorrectFormat;
    private String keyword;

    public FindCommand(boolean isCorrectFormat, String[] commandSplit) throws DukeException{
        this.commandType = CommandType.Find;
        this.isCorrectFormat = isCorrectFormat;
        try {
            this.keyword = commandSplit[1];
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ExceptionType.InvalidFindCommand);
        }

    }

    @Override
    public void executeCommand(TaskList taskList) throws DukeException {
        taskList.findTasks(isCorrectFormat,keyword);
    }
}
