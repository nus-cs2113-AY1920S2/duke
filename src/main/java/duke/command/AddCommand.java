package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.TaskType;
import duke.ui.Ui;

public class AddCommand extends Command {

    private String[] taskInformation;
    private boolean isOneWordCommand;
    private TaskType taskType;

    public AddCommand(String[] taskInformation, boolean isOneWordCommand, TaskType taskType) {
        this.commandType = CommandType.Add;
        this.taskInformation = taskInformation;
        this.isOneWordCommand = isOneWordCommand;
        this.taskType = taskType;
    }

    @Override
    public void executeCommand(TaskList taskList) throws DukeException {
        switch (taskType) {
        case ToDo:
            taskList.addToDoTask(taskInformation,isOneWordCommand);
            break;
        case Deadline:
            taskList.addDeadlineTask(taskInformation,isOneWordCommand);
            break;
        case Event:
            taskList.addEventTask(taskInformation,isOneWordCommand);
            break;
        default:
            Ui.printInvalidCommand();
            break;
        }
    }
}
