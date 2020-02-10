package duke.tasks.exceptions;

public class BadToDoFormatException extends BadTaskFormatException {
    public BadToDoFormatException(String message) {
        super("Bad format for todo: " + message);
    }
}
