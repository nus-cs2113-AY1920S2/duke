package command;

import duke.Duke;

import static misc.Messages.MESSAGE_COMMAND_RESULT_SUCCESS;

public class ListCommand extends Command {
  
    public ListCommand() {
    }
    
    @Override
    public CommandResult execute(Duke duke) {
        duke.executeListCommand();
        return new CommandResult(MESSAGE_COMMAND_RESULT_SUCCESS);
    }
}
