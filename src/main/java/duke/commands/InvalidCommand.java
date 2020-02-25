package duke.commands;

import duke.common.ExceptionMessage;

/**
 * Shows the invalid message
 */
public class InvalidCommand extends Command {
    
    /**
     * Execute the invalid operation flow
     *
     * @return the the invalid message to be shown to user
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(DIVIDER + LS + ExceptionMessage.COMMAND_INVALID_MESSAGE + LS + DIVIDER);
    }
    
}
