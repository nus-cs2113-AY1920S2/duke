package data;

public class NoDescriptionException extends Exception {
    private String message;

    public NoDescriptionException(String messageInput) {
        this.message = messageInput;
    }

    public String getMessage() {
        return this.message;
    }
}
