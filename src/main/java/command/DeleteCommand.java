package command;

import static misc.Messages.MESSAGE_COMMAND_RESULT_SUCCESS;

import duke.Duke;

/**
 * Represents the 'delete' function of a command.
 */
public class DeleteCommand extends Command {
    
    /** The unique ID of a task. */
    private final int taskId;
    
    /** 
     * Constructor of the DeleteCommand. 
     * 
     * @param taskId
     */
    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }
    
    /**
     * Executes the delete function.
     * 
     * @param duke Takes in duke to process the command.
     * @return Returns a result of a command after execution.
     */
    @Override
    public CommandResult execute(Duke duke) {
        duke.executeDeleteCommand(this.taskId);
        return new CommandResult(MESSAGE_COMMAND_RESULT_SUCCESS);
    } 
}
