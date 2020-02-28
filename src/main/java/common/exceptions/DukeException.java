package common.exceptions;

/**
 * An exception which results from incorrect input from the user.
 */
public class DukeException extends Exception {
    
    private static final long serialVersionUID = 1L;
    
    public DukeException() {
        super();
    }
    
    public DukeException(String message) {
        super(message);
    }
}
