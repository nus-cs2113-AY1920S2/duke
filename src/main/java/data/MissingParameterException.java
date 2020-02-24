package data;

public class MissingParameterException extends Exception {
    private String message;

    public MissingParameterException(String messageInput) {
        this.message = messageInput;
    }

    public String getMessage() {
        return this.message;
    }
}
