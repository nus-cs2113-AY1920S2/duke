package command;

import static misc.Messages.MESSAGE_INVALID_TASK_TYPE;
import static misc.Messages.MESSAGE_COMMAND_RESULT_SUCCESS;
import static misc.Messages.MESSAGE_ADD_COMMAND_INVALID_TASK_DESCRIPTION;
import static misc.Messages.MESSAGE_ADD_COMMAND_INVALID_TASK_REQUIREMENT_DEADLINES;
import static misc.Messages.MESSAGE_ADD_COMMAND_INVALID_TASK_REQUIREMENT_EVENTS;

import java.time.LocalDateTime;
import java.util.Optional;

import duke.Duke;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.InvalidTaskArgumentException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDos;

/** 
 * Represents the 'Add' functionality of a command. An AddCommand contains
 * arguments to create a Task, which will then be pass onto Duke to execute the 
 * command. An IllegalTaskArguments will be thrown if the arguments within the 
 * command are invalid.
 * 
 */
public class AddCommand extends Command {
    
    /** Refers to task's keywords such as 'todo', 'deadline' or 'events'. */
    private final String taskType;
    
    /** Refers to an optional description of a task. */
    private final Optional<String> taskDescription;
    
    /** Refers to an optional deadline requirement needed for a task. */
    private final Optional<LocalDateTime> taskDeadline;
    
    /** Refers to an optional starting date and time needed for a task. */
    private final Optional<LocalDateTime> taskStartDateTime;
    
    /** Refers to an optional ending date and time needed for a task. */
    private final Optional<LocalDateTime> taskEndDateTime;
    
    private final Task task;
    
    /** An AddCommand constructor for a ToDos task. */
    public AddCommand(String taskType, Optional<String> taskDescription) {       
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.taskDeadline = Optional.empty();
        this.taskStartDateTime = Optional.empty();
        this.taskEndDateTime = Optional.empty();
        this.task = makeTask(taskType, taskDescription, taskDeadline, 
                taskStartDateTime, taskEndDateTime);        
    }
    
    /** An AddCommand constructor for a Deadlines task. */
    public AddCommand(String taskType, Optional<String> taskDescription, 
            Optional<LocalDateTime> taskDeadline) {
        
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.taskDeadline = taskDeadline;
        this.taskStartDateTime = Optional.empty();
        this.taskEndDateTime = Optional.empty();
        this.task = makeTask(taskType, taskDescription, taskDeadline, 
                taskStartDateTime, taskEndDateTime);        
    }
    
    /** An AddCommand constructor for an Events task. */
    public AddCommand(String taskType, Optional<String> taskDescription,  
            Optional<LocalDateTime> taskStartDateTime,
            Optional<LocalDateTime> taskEndDateTime) {
        
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.taskDeadline = Optional.empty();
        this.taskStartDateTime = taskStartDateTime;
        this.taskEndDateTime = taskEndDateTime;
        this.task = makeTask(taskType, taskDescription, taskDeadline, 
                taskStartDateTime, taskEndDateTime);        
    }  
    
    /** 
     * Creates a task based on task arguments encapsulated within the AddCommand Object. 
     * 
     * @throws InvalidTaskArgumentException If any of the arguments are blank entries.
     */
    private Task makeTask(String taskType, Optional<String> taskDescription, 
            Optional<LocalDateTime> taskDeadline, 
            Optional<LocalDateTime> taskStartDateTime,
            Optional<LocalDateTime> taskEndDateTime) {
        
        Task task;
        
        if (taskDescription.get().isBlank()) {
            throw new InvalidTaskArgumentException(
                    MESSAGE_ADD_COMMAND_INVALID_TASK_DESCRIPTION);
        }
        
        switch(taskType) {
        case "todo":                      
            task = new ToDos(TaskList.taskIdCounter, taskDescription.get());
            break;
        case "deadline":
            if (taskDeadline.isEmpty()) {
                throw new InvalidTaskArgumentException(
                        MESSAGE_ADD_COMMAND_INVALID_TASK_REQUIREMENT_DEADLINES);
            }
            
            task = new Deadlines(TaskList.taskIdCounter, taskDescription.get(), 
                    taskDeadline.get());         
            break;
        case "event":
            if (taskStartDateTime.isEmpty() || taskEndDateTime.isEmpty()) {
                throw new InvalidTaskArgumentException(
                        MESSAGE_ADD_COMMAND_INVALID_TASK_REQUIREMENT_EVENTS);
            }
            task = new Events(TaskList.taskIdCounter, taskDescription.get(), 
                    taskStartDateTime.get(), taskEndDateTime.get());
            break;
        default:
            throw new InvalidTaskArgumentException(MESSAGE_INVALID_TASK_TYPE);
        }
        return task;
    }
    
    @Override
    public CommandResult execute(Duke duke) {
        duke.executeAddCommand(task); 
        return new CommandResult(MESSAGE_COMMAND_RESULT_SUCCESS);
    }
}
