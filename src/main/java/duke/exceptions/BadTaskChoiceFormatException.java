package duke.exceptions;

/**
 * Exception to represent an error when the user specifies a task number that does not currently exist.
 */
public class BadTaskChoiceFormatException extends Exception {
    public BadTaskChoiceFormatException(String errorMessage) {
        super(errorMessage);
    }
}
