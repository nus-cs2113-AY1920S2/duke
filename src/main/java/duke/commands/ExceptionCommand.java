package duke.commands;

/**
 * Execute exception given by user input
 */
public class ExceptionCommand extends Command {
    
    private String exceptionMessage;
    
    /**
     * Constructor for ExceptionCommand
     *
     * @param exceptionMessage takes in a string of exception message
     */
    public ExceptionCommand(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
    
    /**
     * Execute the exception operation flow
     *
     * @return the exception message to be shown to the user
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(exceptionMessage);
    }
    
}
