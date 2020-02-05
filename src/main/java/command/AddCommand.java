package command;

import duke.Duke;
import duke.exception.DukeException;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.InvalidTaskArgumentException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDos;
import java.util.Optional;

import static misc.Messages.MESSAGE_COMMAND_RESULT_SUCCESS;
import static misc.Messages.MESSAGE_ADD_COMMAND_INVALID_TASK_INFO;
import static misc.Messages.MESSAGE_ADD_COMMAND_INVALID_TASK_REQUIREMENT_DEADLINES;
import static misc.Messages.MESSAGE_ADD_COMMAND_INVALID_TASK_REQUIREMENT_EVENTS;

public class AddCommand extends Command {
    
    private final String commandType;
    private final Optional<String> taskInfo;
    private final Optional<String> taskRequirement;
    private final Task task;
    
    public AddCommand(String commandType, Optional<String> taskInfo, 
            Optional<String> taskRequirement) {
        
        this.commandType = commandType;
        this.taskInfo = taskInfo;
        this.taskRequirement  = taskRequirement;
        this.task = makeTask(commandType, taskInfo, taskRequirement);
        
    }
    
    public Task makeTask(String commandType, Optional<String> taskInfo, 
            Optional<String> taskRequirement) {
        Task task;
        
        switch(commandType) {
        case "todo": 
            if (taskInfo.isEmpty()) {
                throw new InvalidTaskArgumentException(
                        MESSAGE_ADD_COMMAND_INVALID_TASK_INFO);
            }
            
            task = new ToDos(TaskList.taskIdCounter, taskInfo.get());
            break;
        case "deadline":
            if (taskInfo.isEmpty()) {
                throw new InvalidTaskArgumentException(
                        MESSAGE_ADD_COMMAND_INVALID_TASK_INFO);
            } else if (taskRequirement.isEmpty()) {
                throw new InvalidTaskArgumentException(
                        MESSAGE_ADD_COMMAND_INVALID_TASK_REQUIREMENT_DEADLINES);
            }
            
            task = new Deadlines(TaskList.taskIdCounter, taskInfo.get(), 
                    taskRequirement.get());         
            break;
        case "event":
            if (taskInfo.isEmpty()) {
                throw new InvalidTaskArgumentException(
                        MESSAGE_ADD_COMMAND_INVALID_TASK_INFO);
            } else if (taskRequirement.isEmpty()) {
                throw new InvalidTaskArgumentException(
                        MESSAGE_ADD_COMMAND_INVALID_TASK_REQUIREMENT_EVENTS);
            } 
            
            task = new Events(TaskList.taskIdCounter, taskInfo.get(), 
                    taskRequirement.get());
            break;
        default:
            throw new DukeException("ERROR");
        }
        return task;
    }
    
    @Override
    public CommandResult execute(Duke duke) {
        duke.executeAddCommand(task); 
        return new CommandResult(MESSAGE_COMMAND_RESULT_SUCCESS);
    }
}
