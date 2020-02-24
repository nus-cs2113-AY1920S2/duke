package data;

public class NoRemarkException extends Exception {
    private String message;

    public NoRemarkException(String messageInput) {
        this.message = messageInput;
    }

    public String getMessage() {
        return this.message;
    }
}
