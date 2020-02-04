package command;

import duke.Duke;
import duke.exception.DukeException;
import duke.task.*;

public class ListCommand extends Command {
  
    public ListCommand() {
    }
    
    @Override
    public CommandResult execute(Duke duke) {
        try {
            duke.executeListCommand();
            return new CommandResult("listing all the tasks...");
        } catch (DukeException e) {
            return new CommandResult("Error in listing tasks");
        }
    }
}
