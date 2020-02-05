package command;

import duke.Duke;
import duke.exception.DukeException;

import static misc.Messages.MESSAGE_COMMAND_RESULT_FAILURE;
import static misc.Messages.MESSAGE_COMMAND_RESULT_SUCCESS;

public class ListCommand extends Command {
  
    public ListCommand() {
    }
    
    @Override
    public CommandResult execute(Duke duke) {
        try {
            duke.executeListCommand();
            return new CommandResult(MESSAGE_COMMAND_RESULT_SUCCESS);
        } catch (DukeException e) {
            return new CommandResult(MESSAGE_COMMAND_RESULT_FAILURE);
        }
    }
}
