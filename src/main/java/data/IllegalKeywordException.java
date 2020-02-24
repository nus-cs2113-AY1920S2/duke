package data;

public class IllegalKeywordException extends Exception {
    private String message;

    public IllegalKeywordException(String messageInput) {
        this.message = messageInput;
    }

    public String getMessage() {
        return this.message;
    }
}
