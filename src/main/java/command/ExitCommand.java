package command;

import duke.Duke;

import static misc.Messages.MESSAGE_COMMAND_RESULT_EXIT;

/** 
 * Represents the 'Exit' functionality of a command. This command
 * serves to exit the program upon executed.  
 * 
 */
public class ExitCommand extends Command {
    
    /** Returns true if a Command is an ExitCommand. */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
    
    @Override
    public CommandResult execute(Duke duke) {
        return new CommandResult(MESSAGE_COMMAND_RESULT_EXIT);
    }
}
