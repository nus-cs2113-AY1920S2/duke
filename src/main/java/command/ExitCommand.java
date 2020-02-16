package command;

import duke.Duke;

import static misc.Messages.MESSAGE_COMMAND_RESULT_EXIT;

/** 
 * Represents the 'exit' function of a command.
 */
public class ExitCommand extends Command {
    
    /** Test if a Command is an ExitCommand. */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
    
    /** 
     * Executes the 'exit' function.
     * 
     * @param duke Takes in duke to process the command.
     * @return Returns a result of a command after execution.
     */
    @Override
    public CommandResult execute(Duke duke) {
        return new CommandResult(MESSAGE_COMMAND_RESULT_EXIT);
    }
}
