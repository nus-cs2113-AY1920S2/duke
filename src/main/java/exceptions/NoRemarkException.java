package exceptions;

/**
 * This custom exception is thrown when the user input has a remarks field but it only contains whitespaces
 */
public class NoRemarkException extends Exception {
    private String message;

    public NoRemarkException(String messageInput) {
        this.message = messageInput;
    }

    public String getMessage() {
        return this.message;
    }
}
