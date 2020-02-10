package duke.tasks.exceptions;

public class BadDeadlineFormatException extends BadTaskFormatException {
    public BadDeadlineFormatException(String message) {
        super("Bad format for deadline: " + message);
    }
}
