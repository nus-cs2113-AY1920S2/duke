package command;

import static misc.Messages.MESSAGE_COMMAND_RESULT_SUCCESS;
import static misc.Messages.MESSAGE_DELETE_COMMAND_MISSING_TASKID;

import java.util.Optional;

import duke.Duke;

/**
 * Represents the 'Delete' functionality of a command. A DeleteCommand contains
 * a TaskID that will be pass onto Duke to delete a task that matches this
 * TaskID. 
 * 
 */
public class DeleteCommand extends Command {
    
    /** The unique ID of a task. */
    private final int taskId;
    
    /** 
     * Constructor of the DeleteCommand. 
     * 
     * @param taskId The ID of a task that is to be deleted.
     * @throws InvalidCommandException If a taskId is a blank entry.
     */
    public DeleteCommand(Optional<String> taskId) {
        boolean hasTaskId = taskId.get().isBlank();

        if (!hasTaskId) {
            this.taskId = Integer.parseInt(taskId.get());
        } else {
            throw new InvalidCommandException(
                    MESSAGE_DELETE_COMMAND_MISSING_TASKID);
        }
    }
    
    @Override
    public CommandResult execute(Duke duke) {
        duke.executeDeleteCommand(this.taskId);
        return new CommandResult(MESSAGE_COMMAND_RESULT_SUCCESS);
    } 
}
