package command;

import duke.Duke;

import static misc.Messages.MESSAGE_COMMAND_RESULT_SUCCESS;

/**
 * Represents the 'done' function of a command.
 */
public class DoneCommand extends Command {
    
    /** The unique ID of a task. */
    private final int taskId;
    
    /**
     * Constructor of a DoneCommand.
     * @param taskId
     */
    public DoneCommand(int taskId) {
        this.taskId = taskId;
    }
    
    /**
     * Executes the 'done' function.
     *
     * @param duke Takes in duke to process the command.
     * @return Returns a result of a command after execution.
     */
    @Override
    public CommandResult execute(Duke duke) {
        duke.executeDoneCommand(this.taskId);
        return new CommandResult(MESSAGE_COMMAND_RESULT_SUCCESS);
    } 
}
