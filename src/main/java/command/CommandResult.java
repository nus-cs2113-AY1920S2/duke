package command;

import static misc.Messages.MESSAGE_INVALID_COMMAND_RESULT;

import duke.Duke;
import duke.exception.DukeException;

public class CommandResult extends Command {
    public final String commandOutput;
    
    public CommandResult(String commandOutput) {
        this.commandOutput = commandOutput;
    }
    
    public String getCommandOutput() {
        return this.commandOutput;
    }
   
    @Override
    public CommandResult execute(Duke duke) {
        throw new DukeException(MESSAGE_INVALID_COMMAND_RESULT);
    }
 
}
