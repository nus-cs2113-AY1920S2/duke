package exceptions;

/**
 * This custom exception is thrown when the user input has a description field but it only contains whitespaces
 */
public class NoDescriptionException extends Exception {
    private String message;

    public NoDescriptionException(String messageInput) {
        this.message = messageInput;
    }

    public String getMessage() {
        return this.message;
    }
}
