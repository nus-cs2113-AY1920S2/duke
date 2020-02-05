package command;

import duke.Duke;

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
        throw new IllegalStateException("Cannot execute a Command Result");
    } 
}
