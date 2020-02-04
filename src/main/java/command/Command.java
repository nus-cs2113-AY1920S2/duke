package command;

import duke.Duke;

public abstract class Command {
    
    public Duke duke;
    
    public abstract CommandResult execute(Duke duke);
}
