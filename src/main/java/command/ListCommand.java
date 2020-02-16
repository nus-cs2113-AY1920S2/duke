package command;

import static misc.Messages.MESSAGE_COMMAND_RESULT_SUCCESS;

import duke.Duke;

/** 
 * Represents the 'list' function for a command.
 */
public class ListCommand extends Command {
  
    public ListCommand() {
    }
    
    /**
     * Executes the 'list' function.
     *
     * @param duke Takes in duke to process the command.
     * @return Returns a result of a command after execution.
     */
    @Override
    public CommandResult execute(Duke duke) {
        duke.executeListCommand();
        return new CommandResult(MESSAGE_COMMAND_RESULT_SUCCESS);
    }
}
