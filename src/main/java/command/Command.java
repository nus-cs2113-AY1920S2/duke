package command;

import duke.Duke;

public abstract class Command {
    
    public abstract CommandResult execute(Duke duke);
}
