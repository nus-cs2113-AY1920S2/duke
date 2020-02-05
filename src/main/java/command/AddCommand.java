package command;

import duke.Duke;
import duke.exception.DukeException;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDos;
import java.util.Optional;

import static misc.Messages.MESSAGE_COMMAND_RESULT_SUCCESS;
import static misc.Messages.MESSAGE_COMMAND_RESULT_FAILURE;

public class AddCommand extends Command {
    
    private final String commandType;
    private final String taskInfo;
    private final Optional<String> taskRequirement;
    private final Task task;
    
    public AddCommand(String commandType, String taskInfo, Optional<String> taskRequirement) {
        this.commandType = commandType;
        this.taskInfo = taskInfo;
        this.taskRequirement  = taskRequirement;
        this.task = makeTask(commandType, taskInfo, taskRequirement);
    }
    
    public Task makeTask(String commandType, String taskInfo, Optional<String> taskRequirement) {
        Task task;
        switch(commandType) {
        case "todo": 
            task = new ToDos(TaskList.taskIdCounter, taskInfo);
            break;
        case "deadline": 
            task = new Deadlines(TaskList.taskIdCounter, taskInfo, taskRequirement.get());         
            break;
        case "event":
            task = new Events(TaskList.taskIdCounter, taskInfo, taskRequirement.get());
            break;
        default:
            throw new DukeException("ERROR");
        }
        return task;
    }
    
    @Override
    public CommandResult execute(Duke duke) {
        try {
            duke.executeAddCommand(task); 
            return new CommandResult(MESSAGE_COMMAND_RESULT_SUCCESS);
        } catch (DukeException e) {
            return new CommandResult(MESSAGE_COMMAND_RESULT_FAILURE);
        }
    }
}
