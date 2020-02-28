package commands;

import static ui.TextUi.printHelpMessage;

/**
 * Shows help instructions. (after user input is wrong)
 */
public class HelpCommand  extends Command {

    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "    Example: " + COMMAND_WORD;

    public HelpCommand() {
    }

    @Override
    public CommandResult execute() {
        printHelpMessage();
        return new CommandResult("");
    }
}
