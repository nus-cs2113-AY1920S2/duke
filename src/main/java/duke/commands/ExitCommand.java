package duke.commands;

public class ExitCommand extends Command {
    
    public static final String COMMAND_WORD = "bye";
    private static final String COMMAND_BYE_DESC = "Exits the program.";
    private static final String COMMAND_BYE_EXAMPLE = COMMAND_WORD;
    
    public static final String MESSAGE_USAGE =
            String.format(MESSAGE_COMMAND_HELP, COMMAND_WORD, COMMAND_BYE_DESC) + LS +
                    String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_BYE_EXAMPLE);
    
    private static final String MESSAGE_EXIT = "You are Exiting Duke at your Request :)";
    
    @Override
    public CommandResult execute() {
        return new CommandResult(DIVIDER + LS + MESSAGE_EXIT);
    }
    
}
