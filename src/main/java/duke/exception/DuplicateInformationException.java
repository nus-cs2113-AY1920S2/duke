package duke.exception;

public abstract class DuplicateInformationException extends IncorrectValueException {

    public DuplicateInformationException(String message) {
        super(message);
    }
}
