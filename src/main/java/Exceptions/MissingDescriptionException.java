package Exceptions;

public class MissingDescriptionException extends DukeException {
    private String errorMessage;

    public MissingDescriptionException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return String.format("Missing Description: '%s' missing a description." +
                " Please enter a description!", errorMessage);
    }
}
