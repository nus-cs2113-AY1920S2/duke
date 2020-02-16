package command;

/** 
 * Represents an exception of an invalid command.
 */
public class InvalidCommandException extends IllegalArgumentException {
    
    /** 
     * Constructor of an InvalidCommandException.
     * 
     * @param message
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
