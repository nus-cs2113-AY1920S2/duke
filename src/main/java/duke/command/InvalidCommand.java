package duke.command;

import duke.utility.Messages;

/**
 * A class representing an invalid command.
 */
public class InvalidCommand extends Command {

    public InvalidCommand () {
        super(null);
    }

    @Override
    public CommandResult execute () {
        return new CommandResult(Messages.INVALID_COMMAND);
    }
}
