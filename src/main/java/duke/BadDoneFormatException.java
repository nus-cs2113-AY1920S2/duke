package duke;

public class BadDoneFormatException extends Exception {
    public BadDoneFormatException(String errorMessage) {
        super("Bad format for keyword done: " + errorMessage);
    }
}
