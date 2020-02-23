package command;

import static misc.Messages.MESSAGE_COMMAND_RESULT_SUCCESS;

import duke.Duke;

/** 
 * Represents the 'List' functionality of a command. A ListCommand does
 * not take in any parameter. It's function is to instruct duke to
 * list all current outstanding tasks that are stored in the storage file.  
 * 
 */
public class ListCommand extends Command {
  
    /** 
     * An empty constructor for a ListCommand that is needed for the 
     * parseCommand() in the Parser class.  
     */ 
    public ListCommand() {
    }
    
    @Override
    public CommandResult execute(Duke duke) {
        duke.executeListCommand();
        return new CommandResult(MESSAGE_COMMAND_RESULT_SUCCESS);
    }
}
