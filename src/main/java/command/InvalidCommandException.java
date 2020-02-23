package command;

/** 
 * Represents an exception of an invalid command. This serves to 
 * inform the user that the command word entered in the launch arguments
 * is invalid.
 * 
 */
public class InvalidCommandException extends IllegalArgumentException {
    
    public InvalidCommandException(String message) {
        super(message);
    }
}
