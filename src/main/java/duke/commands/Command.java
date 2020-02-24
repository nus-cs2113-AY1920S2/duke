package duke.commands;

public abstract class Command implements HelpFormat {
    
    protected Command() {
    }
    
    public CommandResult execute() {
        throw new UnsupportedOperationException("This method to be implemented by child class");
    }
    
}
