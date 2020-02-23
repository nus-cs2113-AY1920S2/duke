package duke.commands;

import static duke.format.Printer.GOODBYE_MESSAGE;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    @Override
    public CommandResult execute() {
        return new CommandResult(GOODBYE_MESSAGE);
    }

    public static boolean isExit (Command command) {
        return command instanceof ExitCommand; // Returns true if command is exit command
    }
}
