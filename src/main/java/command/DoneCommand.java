package command;

import duke.Duke;
import duke.exception.DukeException;

public class DoneCommand extends Command {
    private final int taskId;
    
    public DoneCommand(int taskId) {
        this.taskId = taskId;
    }
    
    @Override
    public CommandResult execute(Duke duke) {
        try {
            duke.executeDoneCommand(taskId);
            return new CommandResult("Completed Task");
        } catch (DukeException e) {
            return new CommandResult("error in completing");
        }
    }
}
