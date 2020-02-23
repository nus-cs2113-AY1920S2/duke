package command;

import duke.Duke;

import static misc.Messages.MESSAGE_COMMAND_RESULT_SUCCESS;
import static misc.Messages.MESSAGE_DONE_COMMAND_MISSING_TASKID;

import java.util.Optional;

/**
 * Represents the 'Done' functionality of a command. A DoneCommand contains
 * a TaskID that will be pass onto Duke to complete a task that matches this
 * TaskID.  
 * 
 */
public class DoneCommand extends Command {
    
    /** The unique ID of a task. */
    private final int taskId;
    
    /**
     * Constructor of a DoneCommand.
     * 
     * @param taskId The ID of a task that is to be completed.
     * @throws InvalidCommandException If the Id of a task is a blank entry.
     */
    public DoneCommand(Optional<String> taskId) {
        boolean hasTaskId = taskId.get().isBlank();

        if (!hasTaskId) {
            this.taskId = Integer.parseInt(taskId.get());
        } else {
            throw new InvalidCommandException(
                    MESSAGE_DONE_COMMAND_MISSING_TASKID);
        }
    }
    
    @Override
    public CommandResult execute(Duke duke) {
        duke.executeDoneCommand(this.taskId);
        return new CommandResult(MESSAGE_COMMAND_RESULT_SUCCESS);
    } 
}
