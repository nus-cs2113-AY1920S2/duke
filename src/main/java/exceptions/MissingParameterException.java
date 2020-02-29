package exceptions;

/**
 * This custom exception is thrown when the user input is missing additional parameters (ie less substrings than expected)
 */
public class MissingParameterException extends Exception {
    private String message;

    public MissingParameterException(String messageInput) {
        this.message = messageInput;
    }

    public String getMessage() {
        return this.message;
    }
}
