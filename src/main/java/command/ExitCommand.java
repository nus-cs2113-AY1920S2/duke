package command;

import duke.Duke;

import static misc.Messages.MESSAGE_COMMAND_RESULT_EXIT;

public class ExitCommand extends Command {
    
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
    
    @Override
    public CommandResult execute(Duke duke) {
        return new CommandResult(MESSAGE_COMMAND_RESULT_EXIT);
    }
}
