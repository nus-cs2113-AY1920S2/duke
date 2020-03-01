package exceptions;

/**
 * Custom Exceptions for when user provides an unknown command
 */
public class UnknownCommandException extends DukeException {
    private String errorMessage;

    /**
     * Constructs an UnknownCommandException object
     * @param errorMessage String provided by user
     */
    public UnknownCommandException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Returns a formatted string
     * @return String formatting the error message
     */
    @Override
    public String toString() {
        return String.format("Unknown Command: '%s' is not a valid command!" +
                " Please enter a valid command!", errorMessage);
    }
}
