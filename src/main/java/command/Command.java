package command;

import duke.Duke;

/**
 * Represents a command.
 */
public abstract class Command {
    
    /** 
     * Execute a command based on the type of command.
     * @param duke Takes in duke to process the command.
     * @return CommandResult Result of executing the command.
     */
    public abstract CommandResult execute(Duke duke);
}
