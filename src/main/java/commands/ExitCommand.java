package commands;


import common.Messages;

/**
 * Terminates the program.
 */

public class ExitCommand extends Command {

    public ExitCommand() {

    }

    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exit the program.\n"
            + "      Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        return new CommandResult(String.format(Messages.MESSAGE_FAREWELL));
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
