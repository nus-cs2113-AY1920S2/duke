package commands;

import ui.TextUi;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    public static final String MESSAGE_EXIT_ACKNOWEDGEMENT = "Exiting Task List as requested ...";

    @Override
    public CommandResult execute() {
        TextUi.showFarewellMessage();
        return null;
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand; // instanceof returns false if it is null
    }
}
