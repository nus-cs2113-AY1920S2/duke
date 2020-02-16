package command;

import duke.Duke;

/**
 * Represents the result after executing a command.
 */
public class CommandResult extends Command {
    
    /** String representation of a result after executing. */
    public final String commandOutput;
    
    /** 
     * Constructor for the CommandResult.
     * 
     * @param commandOutput
     */
    public CommandResult(String commandOutput) {
        this.commandOutput = commandOutput;
    }
    
    public String getCommandOutput() {
        return this.commandOutput;
    }
    
    /**
     * CommandResult is the final stage after executing a command. It cannot be 
     * executed again.
     * 
     * @throws IllegalStateException.
     */
    @Override
    public CommandResult execute(Duke duke) {
        throw new IllegalStateException("Cannot execute a Command Result");
    } 
}
