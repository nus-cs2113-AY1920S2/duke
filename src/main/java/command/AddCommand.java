package command;

import java.util.Optional;

import duke.Duke;
import duke.exception.DukeException;

public class AddCommand extends Command {
    
    private final String commandType;
    private final String taskInfo;
    private final Optional<String> taskRequirement;
    
    public AddCommand(String commandType, String taskInfo, Optional<String> taskRequirement) {
        this.commandType = commandType;
        this.taskInfo = taskInfo;
        this.taskRequirement  = taskRequirement;
    }
    
    @Override
    public CommandResult execute(Duke duke) {
        try {
            duke.executeAddCommand(this.commandType, this.taskInfo, this.taskRequirement);
            return new CommandResult("SUCCESS!");
        } catch (DukeException e) {
            return new CommandResult("ERROR!!! CANNOT ADD");
        }
    }
}
