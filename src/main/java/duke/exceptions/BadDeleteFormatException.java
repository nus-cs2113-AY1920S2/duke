package duke.exceptions;

public class BadDeleteFormatException extends BadTaskChoiceFormatException {
    public BadDeleteFormatException(String errorMessage) {
        super("Bad format for keyword delete: " + errorMessage);
    }
}
