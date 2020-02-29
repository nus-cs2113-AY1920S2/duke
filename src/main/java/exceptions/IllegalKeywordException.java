package exceptions;


/**
 * This custom exception is thrown when the command keyword is not recognised.
 */
public class IllegalKeywordException extends Exception {
    private String message;

    public IllegalKeywordException(String messageInput) {
        this.message = messageInput;
    }

    public String getMessage() {
        return this.message;
    }
}
