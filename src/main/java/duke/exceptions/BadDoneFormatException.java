package duke.exceptions;

public class BadDoneFormatException extends BadTaskChoiceFormatException {
    public BadDoneFormatException(String errorMessage) {
        super("Bad format for keyword done: " + errorMessage);
    }
}
