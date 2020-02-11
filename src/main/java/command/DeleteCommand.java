package command;

import duke.Duke;

import static misc.Messages.MESSAGE_COMMAND_RESULT_SUCCESS;

public class DeleteCommand extends Command {
    private final int taskId;
    
    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }
    
    @Override
    public CommandResult execute(Duke duke) {
        duke.executeDeleteCommand(this.taskId);
        return new CommandResult(MESSAGE_COMMAND_RESULT_SUCCESS);
    } 
}
