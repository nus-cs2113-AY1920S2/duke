package command;

import static misc.Messages.MESSAGE_INVALID_TASK_TYPE;
import static misc.Messages.MESSAGE_COMMAND_RESULT_SUCCESS;
import static misc.Messages.MESSAGE_ADD_COMMAND_INVALID_TASK_INFO;
import static misc.Messages.MESSAGE_ADD_COMMAND_INVALID_TASK_REQUIREMENT_DEADLINES;
import static misc.Messages.MESSAGE_ADD_COMMAND_INVALID_TASK_REQUIREMENT_EVENTS;

import java.util.Optional;

import duke.Duke;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.InvalidTaskArgumentException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDos;

/** 
 * Represents the 'Add' function of a command.
 */
public class AddCommand extends Command {
    
    /** Refers to task's keywords such as 'todo', 'deadline' or 'events'. */
    private final String commandType;
    
    /** Refers to the description of a task. */
    private final Optional<String> taskInfo;
    
    /** Refers to the requirments needed for a task. */
    private final Optional<String> taskRequirement;
    
    private final Task task;
    
    /** 
     * Constructor for an AddCommand.
     * 
     * @param commandType 
     * @param taskInfo
     * @param taskRequirement
     */
    public AddCommand(String commandType, Optional<String> taskInfo, 
            Optional<String> taskRequirement) {
        
        this.commandType = commandType;
        this.taskInfo = taskInfo;
        this.taskRequirement  = taskRequirement;
        this.task = makeTask(commandType, taskInfo, taskRequirement);        
    }
    
    /**
     * Constructs a task based on the parameters provided.
     * 
     * @param commandType 
     * @param taskInfo
     * @param taskRequirement
     * @return Task 
     * @throws InvalidTaskArgumentException Throws an exception if there 
     * are invalid arguments in the parameters.
     */
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
            throw new InvalidTaskArgumentException(MESSAGE_INVALID_TASK_TYPE);
        }
        return task;
    }
    
    /**
     * Executes the add function.
     * 
     * @param duke Takes in duke to process the command.
     * @return Returns a result of a command after execution.
     */
    @Override
    public CommandResult execute(Duke duke) {
        duke.executeAddCommand(task); 
        return new CommandResult(MESSAGE_COMMAND_RESULT_SUCCESS);
    }
}
