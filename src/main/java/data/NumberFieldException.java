package data;

public class NumberFieldException extends Exception {
    private String message;

    public NumberFieldException(String messageInput) {
        this.message = messageInput;
    }

    public String getMessage() {
        return this.message;
    }
}
