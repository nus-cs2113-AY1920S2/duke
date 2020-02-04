package command;

import duke.Duke;

public class ExitCommand extends Command {
    
    public static final String MESSAGE_EXIT_COMMAND = "Shutting down Duke";
    
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
    
    @Override
    public CommandResult execute(Duke duke) {
        return new CommandResult(MESSAGE_EXIT_COMMAND);
    }
}
