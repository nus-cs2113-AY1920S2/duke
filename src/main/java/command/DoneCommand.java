package command;

import duke.Duke;
import duke.exception.DukeException;

import static misc.Messages.MESSAGE_COMMAND_RESULT_FAILURE;
import static misc.Messages.MESSAGE_COMMAND_RESULT_SUCCESS;

public class DoneCommand extends Command {
    private final int taskId;
    
    public DoneCommand(int taskId) {
        this.taskId = taskId;
    }
    
    @Override
    public CommandResult execute(Duke duke) {
        try {
            duke.executeDoneCommand(this.taskId);
            return new CommandResult(MESSAGE_COMMAND_RESULT_SUCCESS);
        } catch (DukeException e) {
            return new CommandResult(MESSAGE_COMMAND_RESULT_FAILURE);
        }
    } 
}
