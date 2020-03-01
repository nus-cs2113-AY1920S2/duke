package Exceptions;

/**
 * Custom Exceptions that throws when user fails to provide date
 */
public class MissingDateException extends DukeException {
    private String errorMessage;

    /**
     * Constructs a MissingDateException object
     * @param errorMessage String provided by user
     */
    public MissingDateException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Returns a formatted string object
     * @return String formatting the error message
     */
    @Override
    public String toString() {
        return String.format("Missing Date: '%s' is missing a date! " +
                "Please enter a date!", errorMessage);
    }
}
