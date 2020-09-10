package duke.exception;

/**
 * Signals an error cause dy Duke
 */
public class DukeException extends Exception {
    
    private static final String DIVIDER = "____________________________________________________________";
    private static final String LS = System.lineSeparator();
    
    /**
     * Constructor for DukeException
     *
     * @param message store exception message to be display to the user
     */
    public DukeException(String message) {
        super(message);
    }
    
    /**
     * Return the information as a string
     *
     * @return the string of exception relevant to the errors
     */
    @Override
    public String toString() {
        return DIVIDER + LS + getMessage() + LS + DIVIDER;
    }
    
}
