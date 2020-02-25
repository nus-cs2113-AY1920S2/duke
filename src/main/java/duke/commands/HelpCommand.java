package duke.commands;

/**
 * Shows help instruction
 */
public class HelpCommand extends Command {
    
    public static final String COMMAND_WORD = "help";
    private static final String COMMAND_HELP_DESC = "Shows the program command line interface instructions";
    private static final String COMMAND_HELP_EXAMPLE = COMMAND_WORD;
    
    public static final String MESSAGE_USAGE =
            String.format(MESSAGE_COMMAND_HELP, COMMAND_WORD, COMMAND_HELP_DESC) + LS +
                    String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_HELP_EXAMPLE) + LS;
    
    /**
     * Execute the help operation flow
     *
     * @return shows the help list to the user
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(DIVIDER + LS + HelpCommand.MESSAGE_USAGE + LS + ListCommand.MESSAGE_USAGE + LS +
                AddCommand.MESSAGE_USAGE + LS + DoneCommand.MESSAGE_USAGE + LS + DeleteCommand.MESSAGE_USAGE + LS +
                ExitCommand.MESSAGE_USAGE + LS + DIVIDER);
    }
    
}
