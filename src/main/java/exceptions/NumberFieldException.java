package exceptions;

/**
 * This custom exception is thrown when the user input for the task number is not an integer or out of range of the current existing tasks
 */
public class NumberFieldException extends Exception {
    private String message;

    public NumberFieldException(String messageInput) {
        this.message = messageInput;
    }

    public String getMessage() {
        return this.message;
    }
}
