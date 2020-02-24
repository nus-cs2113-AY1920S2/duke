package duke.commands;

public class ExceptionCommand extends Command {
    
    private String exceptionMessage;
    
    public ExceptionCommand(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
    
    @Override
    public CommandResult execute() {
        return new CommandResult(exceptionMessage);
    }
    
}
