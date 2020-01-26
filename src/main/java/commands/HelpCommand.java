package commands;

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
        return new CommandResult(
                commands.add.AddTodoCommand.MESSAGE_USAGE
                        + "\n" + "  "+ commands.add.AddDeadlineCommand.MESSAGE_USAGE
                        + "\n" + "  "+ commands.add.AddEventCommand.MESSAGE_USAGE
                        //+ "\n" + "  "+DeleteCommand.MESSAGE_USAGE
                        //+ "\n" + "  "+ClearCommand.MESSAGE_USAGE
                        //+ "\n" + "  "+FindCommand.MESSAGE_USAGE
                        + "\n" + "  "+ ListCommand.MESSAGE_USAGE
                        //+ "\n" + "  "+ ViewCommand.MESSAGE_USAGE
                        //+ "\n" + ViewAllCommand.MESSAGE_USAGE
                        + "\n" + "  "+HelpCommand.MESSAGE_USAGE
                        + "\n" + "  "+ ExitCommand.MESSAGE_USAGE
        );
    }
}
