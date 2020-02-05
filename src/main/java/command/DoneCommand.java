package command;

import duke.Duke;

import static misc.Messages.MESSAGE_COMMAND_RESULT_SUCCESS;

public class DoneCommand extends Command {
    private final int taskId;
    
    public DoneCommand(int taskId) {
        this.taskId = taskId;
    }
    
    @Override
    public CommandResult execute(Duke duke) {
        duke.executeDoneCommand(this.taskId);
        return new CommandResult(MESSAGE_COMMAND_RESULT_SUCCESS);
    } 
}
