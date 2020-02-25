package duke.commands;

/**
 * Represent an executable command
 */
public abstract class Command implements HelpFormat {
    
    /**
     * Empty Command constructor
     */
    protected Command() {
    }
    
    /**
     * Execute the command and return the result
     *
     * @return throw new UnsupportedOperationException if implemented by Command class
     */
    public CommandResult execute() {
        throw new UnsupportedOperationException("This method to be implemented by child class");
    }
    
}
