package command;

import duke.Duke;

/**
 * Represents an abstract command. A command can be executed by
 * specifying Duke as a parameter, which will return a CommandResult.
 * 
 */
public abstract class Command {
    
    /** 
     * Executes a command based on the command type.
     * 
     * @param duke Takes in duke to process the command.
     * @return The result of executing the command.
     */
    public abstract CommandResult execute(Duke duke);
}
