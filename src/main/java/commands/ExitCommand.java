package commands;

import ui.TextUi;

public class ExitCommand extends Command {

    public ExitCommand() {

    }

    public static final String COMMAND_WORD = "bye";

    public static final String MESSAGE_EXIT_ACKNOWEDGEMENT = "  Bye. Hope to see you again soon!";

    @Override
    public CommandResult execute() {
        return new CommandResult(String.format(MESSAGE_EXIT_ACKNOWEDGEMENT));
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand; // instanceof returns false if it is null
    }
}
