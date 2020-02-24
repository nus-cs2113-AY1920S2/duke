package duke.commands;

import duke.common.ExceptionMessage;

public class InvalidCommand extends Command {
    @Override
    public CommandResult execute() {
        return new CommandResult(DIVIDER + LS + ExceptionMessage.COMMAND_INVALID_MESSAGE + LS + DIVIDER);
    }
    
}
