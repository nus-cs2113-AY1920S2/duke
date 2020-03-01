package exceptions;

/**
 * Custom Exceptions for when user fails to provide description
 */
public class MissingDescriptionException extends DukeException {
    private String errorMessage;

    /**
     * Constructs a MissingDescriptionException object
     * @param errorMessage String provided by user
     */
    public MissingDescriptionException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Returns a formatted string
     * @return String formatting the error message
     */
    @Override
    public String toString() {
        return String.format("Missing Description: '%s' missing a description." +
                " Please enter a description!", errorMessage);
    }
}
